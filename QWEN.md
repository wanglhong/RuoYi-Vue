# RuoYi-Vue 项目上下文

## 项目概述

**RuoYi-Vue** 是一套开源的**前后端分离**后台管理系统，采用 Vue 2 + Spring Boot 4 架构。

| 组件 | 技术栈 |
|------|--------|
| 后端 | Java 17+, Spring Boot 4.0.3, Spring Security + JWT |
| 数据库 | MySQL + Druid 连接池 |
| 缓存 | Redis |
| ORM | MyBatis + PageHelper |
| 前端 | Vue 2.6 + Element UI + Vuex |

### 模块结构

```
ruoyi-admin        # Web 入口（启动类所在）
ruoyi-framework    # 框架核心配置
ruoyi-system       # 系统业务（用户、角色、菜单等）
ruoyi-common       # 通用工具类
ruoyi-quartz       # 定时任务
ruoyi-generator    # 代码生成器
ruoyi-ui           # Vue 前端
```

### SDK 模块（供第三方系统集成）

```
system-sdk-core    # SDK 核心模块（依赖 ruoyi-common、ruoyi-system）
system-sdk-db      # SDK 数据库模块（依赖 system-sdk-core、ruoyi-system）
system-sdk-http    # SDK HTTP 模块（依赖 system-sdk-core）
```

**用途**：将 RuoYi 的系统管理功能（用户、角色、菜单、字典等）封装为 SDK，供其他业务系统独立集成使用，无需直接依赖 ruoyi-system 源码。

## 环境要求

- JDK 17+ / Node.js 8.9+ / MySQL 5.7+ / Redis 3.0+ / Maven 3.0+

## 快速启动

### 1. 数据库初始化

```bash
mysql -u root -p < sql/ry_20260321.sql
```

### 2. 修改配置

编辑 `ruoyi-admin/src/main/resources/application-dev.yml`：
- 数据库连接信息
- Redis 连接信息

### 3. 后端启动

```bash
mvn clean package -DskipTests
./ry.sh start   # Linux/macOS
ry.bat          # Windows
java -jar ruoyi-admin/target/ruoyi-admin.jar
```

### 4. 前端启动

```bash
cd ruoyi-ui && npm install && npm run dev
```

## 访问地址

| 服务 | 地址 | 账号 |
|------|------|------|
| 后台 | http://localhost:8080 | admin / admin123 |
| Swagger | http://localhost:8080/swagger-ui.html | - |
| Druid监控 | http://localhost:8080/druid/ | ruoyi / 123456 |

## 开发规范

### 后端 (Java)
- 代码风格遵循 Spring Boot 规范
- Controller 层：`@RestController`，返回 `AjaxResult`
- Service 层：接口 + 实现类
- Mapper 层：使用 MyBatis-Plus 风格
- 分页使用 `PageHelper`

### 前端 (Vue)
- 页面组件放在 `src/views/` 目录
- 路由配置在 `src/router/index.js`
- API 调用在 `src/api/` 目录
- 状态管理使用 Vuex

## 代码生成器

访问 `/tool/gen` 使用内置代码生成器，可一键生成：
- 后端 Controller / Service / Mapper
- 前端 CRUD 页面
- SQL 脚本

## 关键文件

| 文件路径 | 说明 |
|----------|------|
| `RuoYiApplication.java` | 启动类 |
| `application.yml` | 主配置文件 |
| `pom.xml` | Maven 依赖配置 |
| `ruoyi-ui/package.json` | 前端依赖脚本 |
