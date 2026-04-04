package com.ruoyi.sdk;

import com.ruoyi.sdk.plugin.SdkPlugin;
import com.ruoyi.sdk.plugin.SdkPluginManager;
import com.ruoyi.sdk.service.*;

import java.util.logging.Logger;

/**
 * SDK 核心入口类
 * <p>
 * 通过 SPI 机制加载插件，提供统一的服务访问入口
 * </p>
 */
public class SystemSdk {

    private static final Logger log = Logger.getLogger(SystemSdk.class.getName());

    private static final String DB_PLUGIN_CLASS = "com.ruoyi.sdk.db.DbSdkPlugin";
    private static final String HTTP_PLUGIN_CLASS = "com.ruoyi.sdk.http.HttpSdkPlugin";
    private static final String NO_SERVICE_IMPL = "未找到用户服务实现";
    private static final ThreadLocal<String> tokenContext = new ThreadLocal<>();

    private static SdkConfigService sdkConfigService = null;
    private static SdkLogininforService sdkLogininforService = null;
    private static SdkMenuService sdkMenuService = null;
    private static SdkOperLogService sdkOperLogService = null;
    private static SdkRoleService sdkRoleService = null;
    private static SdkUserService sdkUserService = null;

    /**
     * 初始化 SDK
     * <p>
     * 通过 SPI 机制加载所有插件，并从优先级最高的插件中获取服务实现
     * </p>
     */
    public static void init() {
        log.info("正在初始化 System SDK...");
        try {
            // 通过插件管理器加载所有插件
            SdkPluginManager pluginManager = new SdkPluginManager();
            // 获取优先级最高的插件
            SdkPlugin activePlugin = pluginManager.getActivePlugin();
            if (activePlugin == null) {
                log.warning("未找到可用的 SDK 插件");
                return;
            }
            log.info("已激活插件：" + activePlugin.getName() + " (优先级：" + activePlugin.getPriority() + ")");
            // 根据插件类型加载对应的服务实现
            if (DB_PLUGIN_CLASS.equals(activePlugin.getClass().getName())) {
                // DB 插件实现
                loadDbServices();
            } else if (HTTP_PLUGIN_CLASS.equals(activePlugin.getClass().getName())) {
                // HTTP 插件实现
                loadHttpServices();
            } else {
                // 其他插件类型
                throw new RuntimeException("未知插件类型：" + activePlugin.getClass().getName());
            }
            log.info("System SDK 初始化完成");
        } catch (Exception e) {
            throw new RuntimeException("System SDK 初始化失败：" + e.getMessage(), e);
        }
    }

    /**
     * 加载 DB 插件的服务实现
     */
    private static void loadDbServices() {
        log.info("加载 DB 插件服务实现...");
        try {
            sdkConfigService = (SdkConfigService) Class.forName(
                    "com.ruoyi.sdk.db.service.SdkConfigServiceImpl").getDeclaredConstructor().newInstance();
            sdkLogininforService = (SdkLogininforService) Class.forName(
                    "com.ruoyi.sdk.db.service.SdkLogininforServiceImpl").getDeclaredConstructor().newInstance();
            sdkMenuService = (SdkMenuService) Class.forName(
                    "com.ruoyi.sdk.db.service.SdkMenuServiceImpl").getDeclaredConstructor().newInstance();
            sdkOperLogService = (SdkOperLogService) Class.forName(
                    "com.ruoyi.sdk.db.service.SdkOperLogServiceImpl").getDeclaredConstructor().newInstance();
            sdkRoleService = (SdkRoleService) Class.forName(
                    "com.ruoyi.sdk.db.service.SdkRoleServiceImpl").getDeclaredConstructor().newInstance();
            sdkUserService = (SdkUserService) Class.forName(
                    "com.ruoyi.sdk.db.service.SdkUserServiceImpl").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.severe("加载 DB 插件服务失败：" + e.getMessage());
        }
    }

    /**
     * 加载 HTTP 插件的服务实现
     */
    private static void loadHttpServices() {
        log.info("加载 HTTP 插件服务实现...");
        try {
            sdkConfigService = (SdkConfigService) Class.forName(
                    "com.ruoyi.sdk.http.service.SdkConfigServiceImpl").getDeclaredConstructor().newInstance();
            sdkLogininforService = (SdkLogininforService) Class.forName(
                    "com.ruoyi.sdk.http.service.SdkLogininforServiceImpl").getDeclaredConstructor().newInstance();
            sdkMenuService = (SdkMenuService) Class.forName(
                    "com.ruoyi.sdk.http.service.SdkMenuServiceImpl").getDeclaredConstructor().newInstance();
            sdkOperLogService = (SdkOperLogService) Class.forName(
                    "com.ruoyi.sdk.http.service.SdkOperLogServiceImpl").getDeclaredConstructor().newInstance();
            sdkRoleService = (SdkRoleService) Class.forName(
                    "com.ruoyi.sdk.http.service.SdkRoleServiceImpl").getDeclaredConstructor().newInstance();
            sdkUserService = (SdkUserService) Class.forName(
                    "com.ruoyi.sdk.http.service.SdkUserServiceImpl").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.severe("加载 HTTP 插件服务失败：" + e.getMessage());
        }
    }

    /**
     * 获取当前线程的 token
     *
     * @return token
     */
    public static String getToken() {
        return tokenContext.get();
    }

    /**
     * 设置当前线程的 token
     *
     * @param token token
     */
    public static void setToken(String token) {
        tokenContext.set(token);
    }

    /**
     * 清空当前线程的 token
     */
    public static void clearTokenContext() {
        tokenContext.remove();
    }

    public static SdkConfigService getSdkConfigService() {
        if (sdkConfigService == null) init();
        if (sdkConfigService == null) {
            throw new RuntimeException(NO_SERVICE_IMPL);
        }
        return sdkConfigService;
    }

    public static SdkLogininforService getSdkLogininforService() {
        if (sdkLogininforService == null) init();
        if (sdkLogininforService == null) {
            throw new RuntimeException(NO_SERVICE_IMPL);
        }
        return sdkLogininforService;
    }

    public static SdkMenuService getSdkMenuService() {
        if (sdkMenuService == null) init();
        if (sdkMenuService == null) {
            throw new RuntimeException(NO_SERVICE_IMPL);
        }
        return sdkMenuService;
    }

    public static SdkOperLogService getSdkOperLogService() {
        if (sdkOperLogService == null) init();
        if (sdkOperLogService == null) {
            throw new RuntimeException(NO_SERVICE_IMPL);
        }
        return sdkOperLogService;
    }

    public static SdkRoleService getSdkRoleService() {
        if (sdkRoleService == null) init();
        if (sdkRoleService == null) {
            throw new RuntimeException(NO_SERVICE_IMPL);
        }
        return sdkRoleService;
    }

    public static SdkUserService getSdkUserService() {
        if (sdkUserService == null) init();
        if (sdkUserService == null) {
            throw new RuntimeException(NO_SERVICE_IMPL);
        }
        return sdkUserService;
    }

}
