# System SDK SPI 插件机制（纯 Java 实现）

## 概述

System SDK 使用 Java SPI (Service Provider Interface) 机制实现插件化架构，**完全使用原生 Java 实现，不依赖 Spring 框架**。

## 项目结构

```
system-sdk-core/      # SDK 核心模块（定义 SPI 接口）
├── plugin/
│   ├── SdkPlugin.java          # SPI 接口
│   └── SdkPluginManager.java   # 插件管理器
├── config/
│   └── SdkAutoConfiguration.java # 工具类（提供静态方法）
└── service/          # 服务接口定义

system-sdk-db/        # 数据库插件实现（优先级：10）
└── DbSdkPlugin.java

system-sdk-http/      # HTTP 插件实现（优先级：20）
└── HttpSdkPlugin.java
```

## 核心组件

### 1. SdkPlugin 接口

所有 SDK 插件必须实现的 SPI 接口：

```java
public interface SdkPlugin {
    String getName();           // 插件名称
    int getPriority();          // 优先级（数值越小优先级越高）
    void initialize();          // 初始化方法
    void destroy();             // 销毁方法
    boolean isAvailable();      // 是否可用
}
```

### 2. SdkPluginManager

插件管理器负责：
- 通过 Java SPI 自动发现并加载所有插件
- 按优先级排序插件
- 选择优先级最高的可用插件作为主插件
- 提供插件查询和管理功能

### 3. 优先级规则

| 插件 | 优先级 | 说明 |
|------|--------|------|
| system-sdk-db | 10 | 高优先级 |
| system-sdk-http | 20 | 低优先级 |

**当两个插件同时存在时，优先使用 system-sdk-db。**

## 使用方法

### 1. 引入依赖

根据需要引入相应的插件模块：

```xml
<!-- 仅使用数据库插件 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>system-sdk-db</artifactId>
</dependency>

<!-- 仅使用 HTTP 插件 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>system-sdk-http</artifactId>
</dependency>

<!-- 同时使用两个插件（DB 优先） -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>system-sdk-core</artifactId>
</dependency>
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>system-sdk-http</artifactId>
</dependency>
```

### 2. 使用插件管理器

```java
import com.ruoyi.sdk.config.SdkAutoConfiguration;
import com.ruoyi.sdk.plugin.SdkPluginManager;
import com.ruoyi.sdk.plugin.SdkPlugin;

// 获取插件管理器单例
SdkPluginManager pluginManager = SdkAutoConfiguration.getPluginManager();

// 获取当前激活的主插件
SdkPlugin activePlugin = pluginManager.getActivePlugin();

// 获取所有已加载的插件
List<SdkPlugin> allPlugins = pluginManager.getAllPlugins();

// 根据名称获取插件
SdkPlugin dbPlugin = pluginManager.getPluginByName("system-sdk-db");

// 根据类型获取插件
DbSdkPlugin dbPlugin = pluginManager.getPluginByType(DbSdkPlugin.class);

// 检查是否存在某种插件
boolean hasDbPlugin = pluginManager.hasPlugin(DbSdkPlugin.class);
```

### 3. 日志输出示例

启动时会输出类似以下日志：

```
INFO: 正在初始化 system-sdk-db 插件...
INFO: system-sdk-db 插件初始化完成
INFO: 成功加载 SDK 插件：system-sdk-db，优先级：10
INFO: 正在初始化 system-sdk-http 插件...
INFO: system-sdk-http 插件初始化完成
INFO: 成功加载 SDK 插件：system-sdk-http，优先级：20
INFO: 已激活主插件：system-sdk-db，优先级：10
```

## 开发自定义插件

### 1. 实现 SdkPlugin 接口

```java
public class CustomSdkPlugin implements SdkPlugin {
    
    public static final String PLUGIN_NAME = "system-sdk-custom";
    public static final int PLUGIN_PRIORITY = 15; // 介于 DB 和 HTTP 之间
    
    @Override
    public String getName() {
        return PLUGIN_NAME;
    }
    
    @Override
    public int getPriority() {
        return PLUGIN_PRIORITY;
    }
    
    @Override
    public void initialize() {
        // 初始化逻辑
    }
    
    @Override
    public void destroy() {
        // 销毁逻辑
    }
    
    @Override
    public boolean isAvailable() {
        // 检查插件是否可用
        return true;
    }
}
```

### 2. 创建 SPI 配置文件

在 `src/main/resources/META-INF/services/` 目录下创建文件：

**文件名**: `com.ruoyi.sdk.plugin.SdkPlugin`

**文件内容**:
```
com.yourpackage.CustomSdkPlugin
```

### 3. 打包并使用

```bash
mvn clean install
```

然后在主项目中引入你的插件模块即可。

## SPI 机制说明

Java SPI 通过以下方式工作：

1. **服务接口**: `SdkPlugin` 是服务接口
2. **服务提供者**: 各个插件实现类是服务提供者
3. **服务配置文件**: `META-INF/services/com.ruoyi.sdk.plugin.SdkPlugin` 列出所有提供者
4. **服务加载**: `ServiceLoader.load(SdkPlugin.class)` 自动发现并加载所有提供者

## 技术特点

### 纯 Java 实现
- 不使用 Spring 框架
- 不使用 Spring Boot 自动配置
- 使用 `java.util.logging` 进行日志记录
- 使用 `java.util.ServiceLoader` 进行 SPI 加载

### 插件实现技术
- **system-sdk-db**: 使用原生 JDBC 和 Druid 连接池
- **system-sdk-http**: 使用 Java 11 HttpClient

## 注意事项

1. **优先级设置**: 数值越小优先级越高，DB 插件 (10) > HTTP 插件 (20)
2. **插件可用性**: 插件的 `isAvailable()` 方法返回 false 时不会被选为主插件
3. **初始化顺序**: 插件在加载时会自动调用 `initialize()` 方法
4. **销毁顺序**: 应用关闭时会调用所有插件的 `destroy()` 方法
5. **线程安全**: `SdkPluginManager` 是线程安全的

## 故障排查

### 插件未加载

1. 检查 SPI 配置文件路径是否正确：`META-INF/services/com.ruoyi.sdk.plugin.SdkPlugin`
2. 检查配置文件中是否包含完整的实现类名
3. 检查实现类是否有无参构造函数
4. 查看日志中是否有加载错误信息

### 优先级不生效

1. 确认 `getPriority()` 方法返回正确的数值
2. 检查是否有多个插件返回相同的优先级
3. 查看日志确认插件加载顺序

### 插件不可用

1. 检查 `isAvailable()` 方法的实现
2. 确认插件所需的依赖已正确引入
3. 查看日志中的可用性检查信息

## 依赖说明

| 模块 | 依赖 |
|------|------|
| system-sdk-core | ruoyi-common |
| system-sdk-db | mysql-connector-j, druid |
| system-sdk-http | fastjson2 |

所有模块均**不依赖 Spring 框架**，可以在任何 Java 环境中使用。
