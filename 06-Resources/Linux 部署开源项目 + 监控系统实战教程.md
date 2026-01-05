# Linux 部署开源项目 + 监控系统实战教程

本教程以 **部署开源 Spring Boot 项目** + **搭建 Prometheus + Grafana 监控**为例，全程使用 GitHub 开源资源，覆盖从拉取代码、编译部署到监控配置的完整流程，适合 Linux 实战练习。

## 一、 前置准备

1. **Linux 环境**：推荐 CentOS 7/8 或 Ubuntu 20.04+，确保联网，开放端口 `8080（项目）、9090（Prometheus）、3000（Grafana）、9100（node_exporter）`。

   ```bash
   # CentOS 开放端口（若防火墙开启）
   firewall-cmd --zone=public --add-port=8080/tcp --permanent
   firewall-cmd --zone=public --add-port=9090/tcp --permanent
   firewall-cmd --zone=public --add-port=3000/tcp --permanent
   firewall-cmd --zone=public --add-port=9100/tcp --permanent
   firewall-cmd --reload
   
   # Ubuntu 开放端口（若防火墙开启）
   ufw allow 8080/tcp
   ufw allow 9090/tcp
   ufw allow 3000/tcp
   ufw allow 9100/tcp
   ufw reload
   ```

   

2. **安装必备工具**：JDK 11+、Maven、Git、Docker（可选，简化部署）

   ```bash
   # CentOS 安装
   yum install -y java-11-openjdk-devel maven git docker
   systemctl start docker && systemctl enable docker
   
   # Ubuntu 安装
   apt update && apt install -y openjdk-11-jdk maven git docker.io
   systemctl start docker && systemctl enable docker
   ```

 3. **验证安装**

    ```bash
    java -version && mvn -v && git --version && docker --version
    ```



## 二、 选择并拉取 GitHub 开源 Spring Boot 项目

选一个简单无特殊依赖的开源项目，示例用 **`spring-boot-demo`**（轻量级演示项目）：

```bash
# 克隆项目到本地
git clone https://github.com/xkcoding/spring-boot-demo.git
cd spring-boot-demo/demo-helloworld
```

> 也可以自选其他项目，注意查看项目 README 的部署要求。

opt/projects/report/report-core/src/main/resources

## 三、 编译并部署开源项目

### 方式 1：手动编译部署（适合学习）

1. **打包项目**

   ```bash
   # 跳过测试打包，生成 jar 包
   mvn clean package -DskipTests
   # 打包后 jar 包在 target 目录下
   ls target/
   ```
   
2. **后台运行项目**

   ```bash
   # 后台运行，日志输出到 app.log
   #nohup java -jar target/spring-boot-demo-helloworld-2.3.0.RELEASE.jar > app.log 2>&1 &
   nohup java -jar target/demo-helloworld.jar > app.log 2>&1 &
   ```
   
   查看日志输出：
   
   ```bash
   tail -f app.log
   ```
   
   - 应用已通过`nohup`后台启动，日志会写入`app.log`，可通过`tail -f app.log`查看启动状态；
   - 若仍报错，可检查`app.log`中是否有接口映射、端口占用等异常信息。
   
3. **验证部署**

   ```bash
   # 查看进程
   ps -ef | grep java
   # 访问项目接口（替换为服务器 IP）
   curl http://服务器IP:8080/demo/hello
   # 若返回 "Hello World!" 则部署成功
   ```

4. **设置开机自启（系统服务）**

   创建服务文件`/etc/systemd/system/spring-boot-demo.service`

   ```bash
   vi /etc/systemd/system/spring-boot-demo.service
   ```
   
   ```ini
   [Unit]
   Description=Spring Boot Demo Service
   After=network.target docker.service
   
   [Service]
   User=root
   WorkingDirectory=/root/spring-boot-demo/demo-helloworld
   ExecStart=/usr/bin/java -jar target/demo-helloworld.jar
   Restart=always
   RestartSec=5
   
   [Install]
   WantedBy=multi-user.target
   ```
   
   启动并设置开机自启：
   
   ```bash
   systemctl daemon-reload
   systemctl start spring-boot-demo
   systemctl enable spring-boot-demo
   # 查看状态
   systemctl status spring-boot-demo
   ```
   



### 方式 2：Docker 部署（推荐，简化环境依赖）

1. **查看项目 Dockerfile**：若项目自带 `Dockerfile`，直接构建；若无，手动创建：

   ```dockerfile
FROM openjdk:11-jre-slim
   WORKDIR /app
COPY target/spring-boot-demo-helloworld-2.3.0.RELEASE.jar app.jar
   EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
   ```

2. **构建并运行 Docker 镜像**

   ```bash
   # 构建镜像
   docker build -t spring-boot-demo:v1 .
   # 运行容器
   docker run -d -p 8080:8080 --name demo-app spring-boot-demo:v1
   ```
   
   
   
3. **验证 Docker 部署**

   ```bash
   # 构建镜像
   docker build -t spring-boot-demo:v1 .
   # 运行容器
   docker run -d -p 8080:8080 --name demo-app spring-boot-demo:v1

## 四、 搭建监控系统（Prometheus + Grafana + 开源 Exporter）

监控目标：**项目 JVM 指标** + **Linux 系统指标**，全程使用 GitHub 开源组件。

### 步骤 1：改造项目，暴露 Prometheus 监控指标

若开源项目未集成监控，需手动添加依赖（以 Spring Boot 项目为例）：

1. 编辑项目 `pom.xml`，添加 Actuator 和 Prometheus 依赖：

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   <dependency>
       <groupId>io.micrometer</groupId>
       <artifactId>micrometer-registry-prometheus</artifactId>
   </dependency>

   

2. 编辑 `application.yml`，开启监控端点：

   ```yaml
   management:
     endpoints:
       web:
         exposure:
           include: prometheus,health,info
     metrics:
       export:
         prometheus:
           enabled: true
   ```

   
   
3. 重新打包部署项目，验证监控端点：

   ```bash
   curl http://服务器IP:8080/actuator/prometheus
   # 若返回大量指标数据则配置成功

### 步骤 2：安装 Prometheus（GitHub 开源）

1. **下载并解压 Prometheus**（从 GitHub Release 下载最新版本）

   ```bash
   # 替换为最新版本号，可从 https://github.com/prometheus/prometheus/releases 查看
   wget https://github.com/prometheus/prometheus/releases/download/v2.53.1/prometheus-2.53.1.linux-amd64.tar.gz
   tar -zxvf prometheus-2.53.1.linux-amd64.tar.gz
   mv prometheus-2.53.1.linux-amd64 /usr/local/prometheus

2. **修改 Prometheus 配置文件** `/usr/local/prometheus/prometheus.yml`

   ```yaml
   global:
     scrape_interval: 15s  # 采集间隔
   scrape_configs:
     # 监控 Prometheus 自身
     - job_name: "prometheus"
       static_configs:
         - targets: ["localhost:9090"]
     # 监控 Spring Boot 项目
     - job_name: "spring-boot-app"
       static_configs:
         - targets: ["服务器IP:8080"]
     # 监控 Linux 系统（后续安装 node_exporter）
     - job_name: "linux-server"
       static_configs:
         - targets: ["服务器IP:9100"]
   ```
   
3. **后台启动 Prometheus**

   ```bash
nohup /usr/local/prometheus/prometheus --config.file=/usr/local/prometheus/prometheus.yml > prometheus.log 2>&1 &
   ```

4. **验证 Prometheus**：访问 `http://服务器IP:9090`，进入 `Status -> Targets`，查看所有监控目标是否为 `UP` 状态。

### 步骤 3：安装 node_exporter（监控 Linux 系统，GitHub 开源）

```bash
# 下载最新版本，从 https://github.com/prometheus/node_exporter/releases 查看
wget https://github.com/prometheus/node_exporter/releases/download/v1.8.2/node_exporter-1.8.2.linux-amd64.tar.gz
tar -zxvf node_exporter-1.8.2.linux-amd64.tar.gz
mv node_exporter-1.8.2.linux-amd64 /usr/local/node_exporter
# 后台启动
nohup /usr/local/node_exporter/node_exporter > node_exporter.log 2>&1 &
```

> 启动后 Prometheus 会自动采集 `9100` 端口的系统指标。

### 步骤 4：安装 Grafana（可视化监控，GitHub 开源）

1. **下载并安装 Grafana**

   ```bash
   # CentOS 安装
   wget https://dl.grafana.com/oss/release/grafana-11.2.0-1.x86_64.rpm
   yum install -y grafana-11.2.0-1.x86_64.rpm
   
   # Ubuntu 安装
   wget https://dl.grafana.com/oss/release/grafana_11.2.0_amd64.deb
   dpkg -i grafana_11.2.0_amd64.deb

2. **启动 Grafana 并设置开机自启**

   ```bash
   systemctl start grafana-server
   systemctl enable grafana-server
   ```

3. **配置 Grafana**

   - 访问 `http://服务器IP:3000`，默认账号密码 `admin/admin`，登录后修改密码。
   - **添加数据源**：左侧 `Connections -> Data sources -> Add data source`，选择 `Prometheus`，填写地址 `http://服务器IP:9090`，点击 `Save & test`。
   - **导入开源仪表盘**：
     1. 左侧 `Dashboards -> New -> Import`。
     2. 输入 **Spring Boot 监控仪表盘 ID：4701**，选择 Prometheus 数据源，点击 `Import`。
     3. 再输入 **Linux 系统监控仪表盘 ID：8919**，完成导入。
   - 导入后即可看到可视化的监控图表（JVM 内存、CPU、系统负载等）。

## 五、 常用运维命令（Linux 实战必备）

```bash
# 实时查看项目日志
tail -f app.log
# 查看端口占用
netstat -tulpn | grep 8080
# 查看系统负载
top
# 查看内存使用
free -h
# 查看磁盘占用
df -h
# 重启项目服务
systemctl restart spring-boot-demo
# 停止 Docker 容器
docker stop demo-app
```



# 六

## 验证端口是否被占用

可通过以下命令检查 8080 端口是否被其他程序占用：

```bash
# 查看8080端口的占用情况
netstat -tulpn | grep 8080
```

## 查看进程

```bash
# 查看进程
ps -ef | grep java
```

## 防火墙开放 3306 端口

即使之前开放过，可能存在配置未生效的情况：

```bash
# 检查3306端口是否开放
firewall-cmd --list-ports

# 若未开放，重新添加并重载
firewall-cmd --add-port=3306/tcp --permanent
firewall-cmd --reload
```

## 重启 MySQL 服务

```bash
systemctl restart mysqld
```

## 查看MySQL状态

systemctl status [服务名称]

```bash
systemctl status mysqld
```

```bash
● mysqld.service - MySQL Server
     Loaded: loaded (/usr/lib/systemd/system/mysqld.service; enabled; preset: disabled)
     Active: active (running) since Sat 2026-01-03 01:43:48 CST; 1 day 19h ago
       Docs: man:mysqld(8)
             http://dev.mysql.com/doc/refman/en/using-systemd.html
   Main PID: 93772 (mysqld)
     Status: "Server is operational"
      Tasks: 42 (limit: 10308)
     Memory: 47.3M (peak: 483.8M)
        CPU: 11min 21.137s
     CGroup: /system.slice/mysqld.s
     
     ervice
             └─93772 /usr/sbin/mysqld

1月 03 01:43:46 localhost.localdomain systemd[1]: Starting MySQL Server...
1月 03 01:43:48 localhost.localdomain systemd[1]: Started MySQL Server.
```



## 删除服务（以spring-boot-demo的demo-email为例）

要删除`demo-email`服务，需彻底清理进程、文件和相关配置，步骤如下：

### 1. 停止并清理服务进程

```bash
# 1. 查找demo-email的进程ID
ps -ef | grep demo-email.jar

# 2. 强制终止进程（替换<PID>为实际查到的进程号）
kill -9 <PID>

# 3. 验证进程已终止（无输出则清理完成）
ps -ef | grep demo-email.jar
```

### 2. 删除服务相关文件

```bash
# 1. 进入spring-boot-demo目录（若在其他路径则替换）
cd spring-boot-demo

# 2. 删除demo-email模块目录（彻底移除项目文件）
rm -rf demo-email
```

### 3. 清理日志与临时文件（可选）

```bash
# 删除服务日志文件
rm -f email.log
```

完成以上操作后，`demo-email`服务的进程、文件已被完全删除。



## 查找文件

```bash
# 进入项目根目录
cd /opt/projects/Demo

# 查找Demo.java文件
find . -name "Demo.java"
```

