# Maven 完整安装与配置操作手册

## 一、手册说明

本手册适用于**CentOS/RHEL（含 Rocky、Alma Linux）\**和\**Ubuntu/Debian**系列 Linux 系统，涵盖「快速包管理器安装」和「指定版本手动安装」两种方式，同时包含国内镜像加速、环境优化等核心配置，兼顾易用性和灵活性。

## 二、前置准备

1. 已安装 OpenJDK 8/11（Maven 3.6 + 要求 JDK 8 及以上，推荐与项目 JDK 版本一致），验证：`java -version`

2. 具备管理员权限（`sudo`），确保网络通畅（用于下载安装包和依赖）

3. 提前安装基础工具：

   ```bash
   # CentOS/RHEL
   sudo yum install -y wget tar vim
   
   # Ubuntu/Debian
   sudo apt update && sudo apt install -y wget tar vim
   ```

   

## 三、安装 Maven（两种方式二选一）

### 方式一：包管理器快速安装（适合快速部署，版本由系统源提供）

#### 1. CentOS/RHEL 系列

```bash
# 安装Maven
sudo yum install -y maven

# 验证安装（输出版本信息即成功）
mvn -v
```

#### 2. Ubuntu/Debian 系列

```bash
# 更新软件源
sudo apt update

# 安装Maven
sudo apt install -y maven

# 验证安装
mvn -v
```

### 方式二：官方包手动安装（指定版本，推荐生产 / 开发环境）

#### 步骤 1：下载官方稳定版（以 3.9.6 为例，可替换为最新 LTS 版本）

```bash
# 进入统一软件安装目录
cd /opt

# 下载Maven二进制包（若wget下载慢，可手动下载后上传至/opt目录）
sudo wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz

# 解压包
sudo tar -zxvf apache-maven-3.9.6-bin.tar.gz

# 重命名为maven（简化路径，方便后续操作）
sudo mv apache-maven-3.9.6 maven

# 删除压缩包（释放空间）
sudo rm -f apache-maven-3.9.6-bin.tar.gz
```

#### 步骤 2：配置系统环境变量（全局生效）

1. 编辑全局环境变量配置文件

   ```bash
   sudo vi /etc/profile
   ```

   

2. 在文件末尾添加以下内容（指定 Maven 安装路径和二进制命令路径）

   ```bash
   # Maven环境变量配置
   export MAVEN_HOME=/opt/maven
   export PATH=$MAVEN_HOME/bin:$PATH
   ```

   

3. 使环境变量立即生效（无需重启系统）

   ```bash
   source /etc/profile
   
   # 若zsh终端，额外执行：source ~/.zshrc
   ```

   

#### 步骤 3：验证安装结果

```bash
# 输出版本信息、JDK关联信息即表示安装成功
mvn -v
```

成功输出示例：

```tex
Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae9)
Maven home: /opt/maven
Java version: 11.0.21, vendor: Red Hat, Inc., runtime: /usr/lib/jvm/java-11-openjdk-11.0.21.0.9-1.el9_3.x86_64
Default locale: zh_CN, platform encoding: UTF-8
OS name: "linux", version: "5.14.0-70.13.1.el9_0.x86_64", arch: "amd64", family: "unix"
```

## 四、核心配置（国内镜像 + 本地仓库 + 优化参数）

### 说明

- 包管理器安装：`settings.xml` 默认路径为 `/etc/maven/settings.xml`
- 手动安装：`settings.xml` 默认路径为 `/opt/maven/conf/settings.xml`
- 以下配置以「手动安装路径」为例，包管理器安装只需替换对应`settings.xml`路径即可。

### 步骤 1：备份原始配置文件（避免配置错误无法回滚）

```bash
sudo cp /opt/maven/conf/settings.xml /opt/maven/conf/settings.xml.bak
```

### 步骤 2：编辑 settings.xml 配置文件

```bash
sudo vi /opt/maven/conf/settings.xml
```

### 步骤 3：配置国内镜像（阿里云，加速依赖下载）

找到文件中的 `<mirrors>` 节点（若不存在则手动添加），在节点内添加阿里云镜像配置（替换默认的中央仓库）：

```xml
<mirrors>
    <!-- 阿里云Maven镜像（优先推荐） -->
    <mirror>
        <id>aliyunmaven</id>
        <name>Aliyun Maven Repository</name>
        <mirrorOf>central</mirrorOf> <!-- 镜像中央仓库所有请求 -->
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>

    <!-- 可选：添加其他镜像（应对特殊依赖） -->
    <mirror>
        <id>apachemaven</id>
        <name>Apache Maven Repository</name>
        <mirrorOf>central</mirrorOf>
        <url>https://repo.maven.apache.org/maven2</url>
    </mirror>
</mirrors>
```

### 步骤 4：配置本地仓库路径（统一管理依赖包）

找到文件中的 `<settings>` 根节点，添加 `<localRepository>` 节点（指定依赖包存储路径，默认是`~/.m2/repository`）：

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">

    <!-- 配置本地仓库路径（自定义，建议放在/opt/maven下） -->
    <localRepository>/opt/maven/repository</localRepository>

    <!-- 其他原有配置... -->
</settings>
```

### 步骤 5：配置编译默认 JDK 版本（与项目一致，避免版本冲突）

找到文件中的 `<profiles>` 节点，添加 JDK 编译配置（以 JDK 11 为例，可替换为 8）：

```xml
<profiles>
    <!-- 配置默认JDK编译版本 -->
    <profile>
        <id>jdk-11</id>
        <activation>
            <activeByDefault>true</activeByDefault> <!-- 默认激活该配置 -->
            <jdk>11</jdk>
        </activation>
        <properties>
            <maven.compiler.source>11</maven.compiler.source>
            <maven.compiler.target>11</maven.compiler.target>
            <maven.compiler.compilerVersion>11</maven.compiler.compilerVersion>
        </properties>
    </profile>
</profiles>
```

### 步骤 6：保存并退出配置文件

`vi` 编辑器中，按 `Esc` 键，输入 `:wq` 回车（保存并退出）。

### 步骤 7：创建本地仓库目录并授权

```bash
# 创建配置中指定的本地仓库目录
sudo mkdir -p /opt/maven/repository

# 授权（确保普通用户也能读写，避免编译时权限不足）
sudo chmod -R 755 /opt/maven/repository
sudo chown -R $USER:$USER /opt/maven/repository
```

## 五、验证配置有效性

### 步骤 1：执行 Maven 空项目构建（测试依赖下载和配置）

```bash
# 创建临时目录并进入
mkdir -p ~/maven-test && cd ~/maven-test

# 执行Maven快速创建Java项目（测试配置是否生效）
mvn archetype:generate -DgroupId=com.test -DartifactId=maven-demo -Dversion=1.0.0 -Dpackage=com.test -DinteractiveMode=false
```

### 步骤 2：验证配置效果

1. 若命令执行顺利，无长时间卡顿（依赖快速下载），说明阿里云镜像配置生效；
2. 查看 `/opt/maven/repository` 目录，出现大量依赖包文件夹，说明本地仓库配置生效；
3. 进入项目目录 `cd maven-demo`，执行 `mvn clean package -DskipTests`，无 JDK 版本冲突报错，说明 JDK 编译配置生效。

## 六、常用 Maven 命令（快速参考）

```bash
# 查看Maven版本
mvn -v

# 清理项目编译产物（target目录）
mvn clean

# 编译项目（不执行测试）
mvn compile -DskipTests

# 打包项目（生成jar/war包，不执行测试）
mvn clean package -DskipTests

# 安装项目到本地仓库（供其他项目依赖）
mvn clean install -DskipTests

# 查看项目依赖树（排查依赖冲突）
mvn dependency:tree
```

## 七、常见问题排查

1. **环境变量不生效**：重新执行 `source /etc/profile`，或重启终端；检查 `MAVEN_HOME` 路径是否与实际安装路径一致。
2. **依赖下载失败**：检查阿里云镜像地址是否正确，网络是否通畅；可切换至官方镜像重试。
3. **权限不足报错**：确保本地仓库目录 `/opt/maven/repository` 有读写权限，执行 `chmod -R 755 /opt/maven/repository`。
4. **JDK 版本冲突**：检查 `settings.xml` 中 JDK 配置与系统 `java -version` 版本是否一致。

## 八、后续维护

1. **升级 Maven 版本**：手动安装方式下，只需下载新版本解压，替换 `/opt/maven` 目录即可（保留 `conf/settings.xml` 配置）。
2. **清理本地仓库冗余依赖**：执行 `mvn clean repository:clean`（谨慎操作，避免删除有用依赖）。
3. **更新镜像源**：若阿里云镜像无法获取某些特殊依赖，可在 `<mirrors>` 节点添加对应专属镜像。