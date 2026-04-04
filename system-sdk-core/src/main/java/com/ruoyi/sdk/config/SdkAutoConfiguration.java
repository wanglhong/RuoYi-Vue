package com.ruoyi.sdk.config;

import com.ruoyi.sdk.plugin.SdkPluginManager;

/**
 * SDK 工具类
 * <p>
 * 提供获取插件管理器的静态方法
 * 当同时存在 system-sdk-db 和 system-sdk-http 时，优先使用 system-sdk-db
 * </p>
 *
 * @author wlih
 */
public class SdkAutoConfiguration {

    private static final SdkPluginManager pluginManager = new SdkPluginManager();

    private SdkAutoConfiguration() {
        // 私有构造函数，防止实例化
    }

    /**
     * 获取插件管理器实例
     *
     * @return 插件管理器单例
     */
    public static SdkPluginManager getPluginManager() {
        return pluginManager;
    }
}
