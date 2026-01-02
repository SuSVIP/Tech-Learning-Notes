在 Linux 系统中设置 IP 地址，主要有 **临时生效**（重启网络 / 机器后失效）和 **永久生效** 两种方式，下面以主流的 **CentOS/RHEL** 和 **Ubuntu/Debian** 系统为例，分别介绍操作步骤。

# 一、查看当前网络配置（前置步骤）

1. 查看网卡名称

   ```bash
   ip addr
   ```

   输出中类似`eth0`、`ens33`、`enp0s3` 的就是网卡名称，后续操作需要用到。


2. 查看当前 IP 信息

   ```bash
   ifconfig  # 需要安装net-tools工具，CentOS: yum install net-tools -y; Ubuntu: apt install net-tools -y
   ```


# 二、临时设置 IP（重启后失效）

适用于测试场景，无需修改配置文件，命令行直接操作，**所有 Linux 发行版通用**。

```bash
# 1. 设置 IP 地址和子网掩码（示例：网卡ens33，IP 192.168.1.100，子网掩码255.255.255.0）
ip addr add 192.168.1.100/24 dev ens33

# 2. 启用网卡（如果网卡未激活）
ip link set ens33 up

# 3. 设置默认网关
ip route add default via 192.168.1.1

# 4. 设置 DNS（临时生效，修改/etc/resolv.conf）
echo "nameserver 8.8.8.8" > /etc/resolv.conf
echo "nameserver 114.114.114.114" >> /etc/resolv.conf
```

- 验证：`ping 192.168.1.1` 或 `ping www.baidu.com`
- 清除临时 IP：`ip addr del 192.168.1.100/24 dev ens33`

# 三、永久设置 IP（重启后生效）

## 方式 1：CentOS/RHEL 7+（使用 nmcli 命令，推荐）

CentOS 7 以上默认使用 `NetworkManager` 管理网络，用 `nmcli` 命令配置更简单。

1. 查看网络连接名称

   ```bash
nmcli connection show
   ```
   
   输出中 `NAME` 列即为连接名，通常和网卡名一致（如 `ens33`）。

2. 配置静态 IP

   ```bash
   # 修改连接配置：设置IP、子网掩码、网关、DNS
   nmcli connection modify ens33 \
   ipv4.method manual \
   ipv4.addresses 192.168.1.100/24 \
   ipv4.gateway 192.168.1.1 \
   ipv4.dns "8.8.8.8,114.114.114.114" \
   connection.autoconnect yes
   ```
   
3. 重启网络连接

   ```bash
   nmcli connection down ens33 && nmcli connection up ens33
   ```
   
4. 验证：`ip addr show ens33`

## 方式 2：CentOS/RHEL 7+（修改网卡配置文件）

网卡配置文件路径：`/etc/sysconfig/network-scripts/ifcfg-<网卡名>`（如 `ifcfg-ens33`）。

1. 编辑配置文件

   ```bash
   vi /etc/sysconfig/network-scripts/ifcfg-ens33
   ```
   
2. 修改为以下内容（根据实际情况调整）

   ```conf
   TYPE=Ethernet
   BOOTPROTO=static  # 静态IP，dhcp为自动获取
   IPADDR=192.168.1.100  # 静态IP地址
   NETMASK=255.255.255.0  # 子网掩码
   GATEWAY=192.168.1.1  # 网关
   DNS1=8.8.8.8  # DNS服务器1
   DNS2=114.114.114.114  # DNS服务器2
   NAME=ens33
   DEVICE=ens33
   ONBOOT=yes  # 开机自动激活网卡
   ```
   
3. 重启网络服务

   ```bash
   # CentOS 7+
   systemctl restart NetworkManager
   # 或
   nmcli connection reload
   ```


## 方式 3：Ubuntu/Debian 18.04+（修改 netplan 配置文件）

Ubuntu 18.04 以上默认使用 `netplan` 管理网络，配置文件路径：`/etc/netplan/*.yaml`（通常是 `00-installer-config.yaml`）。

1. 编辑 netplan 配置文件

   ```bash
   vi /etc/netplan/00-installer-config.yaml
   ```
   
2. 修改为以下内容（注意 **yaml 文件严格缩进，不能用 tab**）

   ```yaml
   network:
     ethernets:
       ens33:  # 网卡名称
         addresses: [192.168.1.100/24]  # 静态IP和子网掩码
         gateway4: 192.168.1.1  # 网关
         nameservers:
           addresses: [8.8.8.8, 114.114.114.114]  # DNS服务器
     version: 2
   ```

3. 应用配置

   ```bash
   netplan apply
   ```
   
4. 验证：`ip addr show ens33`

# 四、常见问题排查

1. 配置后无法上网
   - 检查网关是否正确：`ping 网关IP`
   - 检查 DNS 是否配置：`cat /etc/resolv.conf`
   - 检查防火墙是否拦截：`systemctl stop firewalld`（CentOS）或 `ufw disable`（Ubuntu）测试
2. 配置文件修改后不生效
   - 检查配置文件语法是否正确（尤其是 yaml 文件的缩进）
   - 重启网络服务或机器
   - 确认 `ONBOOT=yes`（CentOS）或 `connection.autoconnect yes`（nmcli）



----

# Rocky Linux的IP配置

**Rocky Linux** 属于 RHEL 系（和 CentOS 用法一致），修改 `ens160` 网卡的 IP 可以用 **`nmcli` 命令（推荐）** 或 **修改网卡配置文件**，以下是详细步骤：

## 一、先确认网卡状态

从你的 `ip addr` 输出看，`ens160` 是 `state DOWN`（未激活），先记下来，后续配置后要启动它。

## 二、方式 1：用 `nmcli` 命令（简单快捷，推荐）

`nmcli` 是 RHEL 系默认的网络管理工具，配置后立即生效且永久保存。

### 步骤 1：查看当前网卡连接名

```bash
sudo nmcli connection show
```

输出里会有 `ens160` 对应的连接名（一般和网卡名一致，比如 `ens160`）。

### 步骤 2：修改 IP、网关、DNS（替换成你的实际信息）

```bash
# 替换以下参数：
# 192.168.1.100/24 → 你的目标IP+子网掩码
# 192.168.1.1 → 你的网关
# 8.8.8.8,114.114.114.114 → 你的DNS
sudo nmcli connection modify ens160 \
  ipv4.addresses 192.168.1.100/24 \
  ipv4.gateway 192.168.1.1 \
  ipv4.dns 8.8.8.8,114.114.114.114 \
  ipv4.method manual  # 手动配置（自动是dhcp）
```

### 步骤 3：启动网卡并生效配置

```bash
# 启动ens160网卡
sudo nmcli connection up ens160
```

## 三、方式 2：修改网卡配置文件（传统方式）

配置文件路径是 `/etc/sysconfig/network-scripts/ifcfg-ens160`（`ens160` 是你的网卡名）。

### 步骤 1：编辑配置文件

```bash
sudo vi /etc/sysconfig/network-scripts/ifcfg-ens160
```

### 步骤 2：修改 / 添加以下内容

把文件里的对应项改成下面的配置（没有的项直接新增）：

```ini
TYPE=Ethernet
BOOTPROTO=static  # 静态IP（自动是dhcp）
NAME=ens160
DEVICE=ens160
ONBOOT=yes  # 开机自动激活网卡
IPADDR=192.168.1.100  # 你的目标IP
NETMASK=255.255.255.0  # 子网掩码（对应/24）
GATEWAY=192.168.1.1  # 你的网关
DNS1=8.8.8.8
DNS2=114.114.114.114
```

### 步骤 3：重启网络服务生效

```bash
sudo systemctl restart NetworkManager
```

## 四、验证是否生效

执行以下命令，看 `ens160` 是否显示你设置的 IP：

```bash
ip addr show ens160
```

如果你的网络是 DHCP 自动获取（不是静态 IP），只需要把 `ipv4.method` 改成 `auto`（`nmcli` 方式），或 `BOOTPROTO=dhcp`（配置文件方式）即可。



---

# Rocky Linux 网卡 IP（静态）配置快速脚本

该脚本适用于你的 `ens160` 网卡，支持一键配置静态 IP、网关、DNS，配置完成后自动生效并验证，全程无需手动编辑文件，复制到终端直接执行即可。

## 一、脚本内容（直接复制粘贴到 Rocky Linux 终端）

```bash
#!/bin/bash
# Rocky Linux ens160 网卡静态IP配置脚本
# 请先修改下方的IP、网关、DNS配置参数，再执行脚本

# ====================== 配置参数（请根据你的网络环境修改）======================
TARGET_IP="192.168.1.131/24"  # 目标IP+子网掩码（例：192.168.0.200/24）
GATEWAY="192.168.1.1"         # 网关地址（例：192.168.0.1）
DNS_SERVERS="8.8.8.8,114.114.114.114"  # DNS服务器，多个用逗号分隔
NETWORK_CARD="ens160"         # 网卡名（无需修改，对应你的网卡）
# ==============================================================================

# 检查是否为root权限
if [ $EUID -ne 0 ]; then
    echo "错误：请使用root权限执行该脚本（在命令前加sudo）"
    exit 1
fi

# 步骤1：备份原有网卡配置（防止配置出错可回滚）
BACKUP_PATH="/etc/sysconfig/network-scripts/ifcfg-${NETWORK_CARD}.bak_$(date +%Y%m%d%H%M%S)"
cp /etc/sysconfig/network-scripts/ifcfg-${NETWORK_CARD} ${BACKUP_PATH} 2>/dev/null
echo "已备份原有网卡配置到：${BACKUP_PATH}"

# 步骤2：使用nmcli配置静态IP、网关、DNS
echo "正在配置网卡${NETWORK_CARD}..."
nmcli connection modify ${NETWORK_CARD} \
    ipv4.addresses ${TARGET_IP} \
    ipv4.gateway ${GATEWAY} \
    ipv4.dns ${DNS_SERVERS} \
    ipv4.method manual \
    connection.autoconnect yes

# 步骤3：启动/重启网卡使配置生效
echo "正在启动网卡${NETWORK_CARD}..."
nmcli connection up ${NETWORK_CARD}

# 步骤4：验证配置是否生效
echo -e "\n==================== 配置结果验证 ===================="
echo "网卡${NETWORK_CARD}当前IP信息："
ip addr show ${NETWORK_CARD} | grep -E "inet |state "

echo -e "\n当前网关信息："
ip route show | grep default

echo -e "\n配置完成！若未显示目标IP，请检查配置参数是否符合你的网络环境。"
```

## 二、使用说明

### 1. 前置准备

1. 打开 Rocky Linux 终端，确保能正常执行命令。
2. 根据你的**实际网络环境**，修改脚本中「配置参数」部分的内容：
   - `TARGET_IP`：改成你想要设置的 IP（比如 `192.168.0.200/24`，`/24` 对应子网掩码 `255.255.255.0`）。
   - `GATEWAY`：改成你的路由器 / 网关 IP（比如 `192.168.0.1`）。
   - `DNS_SERVERS`：可保留默认，也可改成你的内网 DNS。

### 2. 执行脚本

1. 把修改后的完整脚本复制到终端，直接回车执行。
2. 若提示权限不足，在脚本最前面加 `sudo`，即：`sudo bash -c '粘贴修改后的完整脚本'`。

### 3. 脚本功能说明

1. 自动备份原有网卡配置（带时间戳，出错可回滚）。
2. 无需手动编辑配置文件，通过 `nmcli` 完成所有配置（Rocky Linux 推荐方式，配置永久生效）。
3. 自动启动网卡，避免手动执行重启命令。
4. 配置完成后自动验证 IP 和网关，直观查看是否生效。

## 三、回滚方案（若配置出错）

如果配置后网络异常，可通过备份文件恢复原有配置：

```bash
# 替换下面的备份文件名（复制脚本执行时输出的备份路径）
sudo cp /etc/sysconfig/network-scripts/ifcfg-ens160.bak_20260102165500 /etc/sysconfig/network-scripts/ifcfg-ens160

# 重启网卡
sudo nmcli connection up ens160
```

## 四、补充（若需配置 DHCP 自动获取 IP）

如果不需要静态 IP，想要自动获取 IP，执行以下简化脚本即可：

```bash
#!/bin/bash
NETWORK_CARD="ens160"
if [ $EUID -ne 0 ]; then
    echo "错误：请使用root权限执行该脚本"
    exit 1
fi
nmcli connection modify ${NETWORK_CARD} \
    ipv4.method auto \
    connection.autoconnect yes
nmcli connection up ${NETWORK_CARD}
echo "已配置${NETWORK_CARD}为DHCP自动获取IP，配置生效！"
```