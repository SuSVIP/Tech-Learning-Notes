## 目录结构

```
Tech-Learning-Notes/
│
├── README.md                # 仓库总说明（学习路线、目录索引、联系方式）
├──LICENSE                   # 开源协议（可选）
│
├── 00-StudyPlan/            # 学习计划与路线
│   ├── frontend-roadmap.md  # 前端学习路线
│   ├── backend-roadmap.md   # 后端学习路线
│   └── daily-plan.md        # 每日学习计划（可按日期更新）
│
├── 01-Frontend/             # 前端技术
│   ├── 01-HTML-CSS/
│   │   ├── code/            # 示例代码
│   │   └── notes/           # 学习笔记（Markdown）
│   ├── 02-JavaScript/
│   │   ├── code/
│   │   └── notes/
│   ├── 03-Vue/
│   │   ├── code/
│   │   └── notes/
│   └── 04-React/
│       ├── code/
│       └── notes/
│
├── 02-Backend/              # 后端技术
│   ├── 01-JavaSE/
│   │   ├── code/
│   │   └── notes/
│   ├── 02-JavaEE/
│   │   ├── code/
│   │   └── notes/
│   ├── 03-Spring/
│   │   ├── code/
│   │   └── notes/
│   ├── 04-MyBatis/
│   │   ├── code/
│   │   └── notes/
│   └── 05-SpringBoot/
│       ├── code/
│       └── notes/
│
├── 03-Database/           # 数据库
│   ├── 01-MySQL/
│   │   ├── code/          # SQL脚本
│   │   └── notes/
│   └── 02-Redis/
│       ├── code/
│       └── notes/
│
├── 04-Linux/              # Linux 学习
│   ├── code/              # Shell脚本
│   └── notes/
│
├── 05-Projects/           # 综合项目
│   ├── 01-SpringBoot-Vue-Demo/
│   │   ├── backend/
│   │   ├── frontend/
│   │   └── README.md
│   └── 02-Online-Shop/
│       ├── backend/
│       ├── frontend/
│       └── README.md
│
└── 06-Resources/          # 学习资源
    ├── books.md
    ├── video-courses.md
    └── useful-websites.md
```



## 内容规范

### 1. 代码文件夹（`code/`）

- 每个技术点的示例代码单独放在一个文件夹，如 `Spring-IoC-Demo`。
- Java 项目必须带 `pom.xml` 或 `build.gradle`，前端项目带 `package.json`。
- 数据库相关代码放在 `sql` 子文件夹。
- 代码必须**可直接运行**，并在 README 中写运行步骤。

### 2. 笔记文件夹（`notes/`）

- 使用 **Markdown**（`.md`）格式。

- 命名建议：`YYYYMMDD-主题.md`（如 `20240928-SpringBoot自动配置.md`）。

- 笔记结构建议：

  ```
  # 学习目标
  ...
  
  # 核心知识点
  - 知识点1
  - 知识点2
  
  # 代码示例
  ```java
  public class Demo {
      public static void main(String[] args) {
          System.out.println("Hello");
      }
  }
  ```

  

  ## README.md 模板

  ```
  # 技术学习仓库
  
  📚 全栈技术学习笔记与代码示例，包含前端、后端、数据库、Linux等。
  
  ## 技术栈
  - **前端**：HTML、CSS、JavaScript、Vue、React
  - **后端**：JavaSE、JavaEE、Spring、MyBatis、SpringBoot
  - **数据库**：MySQL、Redis
  - **运维/工具**：Linux、Git、Maven
  
  ## 学习路线
  - [前端学习路线](00-StudyPlan/frontend-roadmap.md)
  - [后端学习路线](00-StudyPlan/backend-roadmap.md)
  
  ## 项目实战
  - [SpringBoot + Vue 前后端分离示例](05-Projects/01-SpringBoot-Vue-Demo)
  - [在线商城项目](05-Projects/02-Online-Shop)
  
  ## 资源推荐
  - [书籍](06-Resources/books.md)
  - [视频课程](06-Resources/video-courses.md)
  - [实用网站](06-Resources/useful-websites.md)
  ```

  

  ## 使用建议

  1. **每次学习后提交**：

     ```bash
     git add .
     git commit -m "feat: 新增SpringBoot整合MyBatis示例"
     git push
     ```

  2. **笔记和代码同步**：学完一个知识点，马上写笔记 + 保存可运行的代码。

  3. **项目实战驱动**：学完零散技术后，做一个综合项目，把前后端和数据库结合起来。

  4. **保持更新**：即使是旧技术，也可以不断优化代码和笔记。



## 核心文件内容模板

### 1. 根目录 `README.md`（仓库总入口）

```
# FullStack-Learning-Repo
📚 全栈技术学习仓库：包含前端、后端、数据库、Linux 等技术的代码示例与学习笔记，适合全栈初学者系统性学习。

## 仓库定位
- 记录个人学习过程，方便复盘与复习
- 提供可直接运行的代码示例，避免“看会了但写不出来”
- 汇总优质学习资源，帮助同类学习者少走弯路

## 技术栈清单
| 分类       | 包含技术                          | 学习状态 |
|------------|-----------------------------------|----------|
| 前端       | HTML/CSS、JavaScript、Vue3、React | 进行中   |
| 后端       | JavaSE、JavaEE、Spring、SpringBoot | 进行中   |
| 数据库     | MySQL、Redis                      | 进行中   |
| 运维/工具  | Linux、Git、Maven                 | 进行中   |

## 目录导航
1. [学习计划](00-StudyPlan/) - 前端/后端学习路线、每日计划
2. [前端技术](01-Frontend/) - HTML/CSS/JS/Vue/React 代码与笔记
3. [后端技术](02-Backend/) - Java/SSM/SpringBoot 代码与笔记
4. [数据库](03-Database/) - MySQL 脚本、Redis 操作示例
5. [Linux](04-Linux/) - Shell 脚本、Linux 命令笔记
6. [综合项目](05-Projects/) - 前后端结合实战（含启动说明）
7. [学习资源](06-Resources/) - 推荐书籍/视频/网站

## 使用说明
- 代码文件夹（`code/`）：每个示例代码均可独立运行，需注意依赖配置（如 Java 项目的 `pom.xml`、前端项目的 `package.json`）
- 笔记文件夹（`notes/`）：按「日期+主题」命名（如 `20241001-Vue3-组件通信.md`），包含学习目标、核心知识点、问题解决记录
- 项目文件夹（`05-Projects/`）：每个项目含独立 `README.md`，说明功能、技术栈、启动步骤

## 贡献与交流
- 若发现代码错误或笔记遗漏，欢迎提 Issue 或 Pull Request
- 学习过程中遇到问题，可在仓库评论区交流

## 许可证
[MIT](LICENSE)
```

### 2. `00-StudyPlan/frontend-roadmap.md`（前端学习路线）

```
# 前端学习路线（3-4个月规划）
## 第一阶段：基础入门（1个月）
1. **HTML/CSS**
   - 掌握：HTML 语义化标签、CSS 选择器、盒模型、Flex/Grid 布局
   - 实践：制作静态页面（如个人简历页、产品介绍页）
   - 笔记：[HTML/CSS 学习笔记](../01-Frontend/01-HTML-CSS/notes/)
   - 代码：[HTML/CSS 示例代码](../01-Frontend/01-HTML-CSS/code/)

2. **JavaScript 基础**
   - 掌握：变量/函数、数组/对象、DOM 操作、事件监听、异步（Promise/async-await）
   - 实践：实现简单交互（如表单验证、倒计时、TodoList）
   - 笔记：[JavaScript 学习笔记](../01-Frontend/02-JavaScript/notes/)
   - 代码：[JavaScript 示例代码](../01-Frontend/02-JavaScript/code/)

## 第二阶段：框架学习（1.5个月）
1. **Vue3 全家桶**
   - 掌握：Vue3 语法（Setup、Ref/Reactive）、组件通信、Vue Router、Pinia、Axios
   - 实践：制作后台管理系统（含登录、数据列表、新增/编辑功能）
   - 笔记：[Vue 学习笔记](../01-Frontend/03-Vue/notes/)
   - 代码：[Vue 示例代码](../01-Frontend/03-Vue/code/)

2. **React 基础**
   - 掌握：JSX 语法、组件生命周期、Hooks（useState/useEffect）、React Router、Redux
   - 实践：制作 TodoList 进阶版（含状态管理、本地存储）
   - 笔记：[React 学习笔记](../01-Frontend/04-React/notes/)
   - 代码：[React 示例代码](../01-Frontend/04-React/code/)

## 第三阶段：项目实战（0.5-1个月）
- 目标：前后端分离项目（对接 SpringBoot 后端）
- 项目：[SpringBoot-Vue-Demo](../05-Projects/01-SpringBoot-Vue-Demo/)
- 重点：接口调用、跨域处理、权限控制、前端性能优化
```

### 3. `00-StudyPlan/backend-roadmap.md`（后端学习路线）

```
# 后端学习路线（4-5个月规划）
## 第一阶段：Java 基础（1个月）
1. **JavaSE 核心**
   - 掌握：面向对象（类/继承/多态）、集合框架（List/Map/Set）、IO流、多线程、JVM 基础
   - 实践：实现简易文件管理器、多线程下载工具
   - 笔记：[JavaSE 学习笔记](../02-Backend/01-JavaSE/notes/)
   - 代码：[JavaSE 示例代码](../02-Backend/01-JavaSE/code/)

2. **JavaEE 基础**
   - 掌握：Servlet、JSP、Filter、Listener、MVC 模式
   - 实践：制作简易登录注册系统（含数据库连接）
   - 笔记：[JavaEE 学习笔记](../02-Backend/02-JavaEE/notes/)
   - 代码：[JavaEE 示例代码](../02-Backend/02-JavaEE/code/)

## 第二阶段：框架与数据库（2个月）
1. **Spring 全家桶**
   - Spring：IoC 容器、依赖注入、AOP、事务管理
   - SpringMVC：请求映射、参数绑定、拦截器、文件上传
   - MyBatis：Mapper 配置、动态 SQL、分页、缓存
   - 实践：SSM 框架整合（实现用户管理系统）
   - 笔记：[Spring 笔记](../02-Backend/03-Spring/notes/)、[MyBatis 笔记](../02-Backend/04-MyBatis/notes/)
   - 代码：[Spring 示例](../02-Backend/03-Spring/code/)、[MyBatis 示例](../02-Backend/04-MyBatis/code/)

2. **SpringBoot**
   - 掌握：自动配置、Starter 依赖、配置文件、集成 MyBatis/Redis、Swagger
   - 实践：制作 RESTful API 接口（用户/订单模块）
   - 笔记：[SpringBoot 学习笔记](../02-Backend/05-SpringBoot/notes/)
   - 代码：[SpringBoot 示例代码](../02-Backend/05-SpringBoot/code/)

3. **数据库**
   - MySQL：SQL 语法、索引、事务隔离级别、慢查询优化
   - Redis：数据类型、缓存策略、分布式锁、Java 操作 Redis
   - 实践：实现 MySQL 分表、Redis 缓存用户信息
   - 笔记：[MySQL 笔记](../03-Database/01-MySQL/notes/)、[Redis 笔记](../03-Database/02-Redis/notes/)
   - 代码：[MySQL 脚本](../03-Database/01-MySQL/code/)、[Redis 示例](../03-Database/02-Redis/code/)

## 第三阶段：运维与项目（1-1.5个月）
1. **Linux**
   - 掌握：常用命令（cd/ls/grep）、服务管理（Nginx/MySQL）、Shell 脚本
   - 实践：编写 JDK/MySQL 自动安装脚本
   - 笔记：[Linux 学习笔记](../04-Linux/notes/)
   - 代码：[Shell 脚本](../04-Linux/code/)

2. **综合项目**
   - 目标：前后端分离商城项目（含支付、订单、商品模块）
   - 项目：[Online-Shop](../05-Projects/02-Online-Shop/)
   - 重点：分布式部署、接口安全、性能优化
```

### 4. `05-Projects/01-SpringBoot-Vue-Demo/README.md`（基础项目说明）

```
# SpringBoot-Vue-Demo（基础前后端分离示例）
## 项目介绍
- 功能：用户登录、用户列表查询、新增/编辑/删除用户
- 技术栈：
  - 后端：SpringBoot 2.7 + MyBatis + MySQL 8.0
  - 前端：Vue3 + Vue Router + Axios + Element Plus
- 适合人群：刚学完 SpringBoot 和 Vue3，想练手前后端对接的初学者

## 启动步骤
### 1. 后端启动（SpringBoot）
1. 导入数据库脚本：`backend/src/main/resources/db.sql`（创建数据库 `demo_db`，执行建表语句）
2. 修改配置文件：`backend/src/main/resources/application.yml`，替换 MySQL 用户名/密码
3. 运行主类：`com.demo.SpringBootDemoApplication`
4. 接口测试：访问 `http://localhost:8080/swagger-ui.html`，可查看所有接口

### 2. 前端启动（Vue3）
1. 安装依赖：进入 `frontend` 目录，执行 `npm install`
2. 启动项目：执行 `npm run dev`
3. 访问页面：打开浏览器访问 `http://localhost:5173`（默认端口）

## 核心接口
| 接口地址          | 方法 | 功能               |
|-------------------|------|--------------------|
| /api/user/login   | POST | 用户登录（返回Token）|
| /api/user/list    | GET  | 查询用户列表        |
| /api/user/add     | POST | 新增用户           |
| /api/user/update  | PUT  | 编辑用户           |
| /api/user/delete  | DELETE | 删除用户          |

## 注意事项
- 跨域处理：后端已配置 CORS，允许前端 `http://localhost:5173` 访问
- 权限控制：登录后需在请求头携带 `Authorization: Bearer {Token}` 访问其他接口
```

## 后续使用建议

1. **按学习进度填充内容**：学完一个知识点后，立即在对应 `code/` 文件夹写示例代码，在 `notes/` 文件夹写学习笔记（笔记命名格式：`20241001-XXX知识点.md`）。
2. **保持代码可运行**：每个代码示例需单独可运行，避免依赖其他未提交的代码；Java 项目需带 `pom.xml`，前端项目带 `package.json`。
3. **定期更新学习计划**：在 `daily-plan.md` 中记录每日学习内容（如 `2024-10-01：学习 Vue3 组件通信，完成 2 个示例代码`），方便追踪进度。

按这个模板搭建后，你的学习仓库会非常规整，后续复习或求职时，也能直接将仓库链接附在简历上，展示你的学习成果！

