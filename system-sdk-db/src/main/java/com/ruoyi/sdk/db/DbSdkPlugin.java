package com.ruoyi.sdk.db;

import com.ruoyi.sdk.plugin.SdkPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库 SDK 插件实现
 * <p>
 * 调用 ruoyi-system 模块中的 Service 层逻辑实现 SDK 功能
 * 优先级：10（高于 HTTP 插件）
 * </p>
 *
 * @author wlih
 */
public class DbSdkPlugin implements SdkPlugin {

    private static final Logger log = LoggerFactory.getLogger(DbSdkPlugin.class);

    /**
     * 插件名称
     */
    public static final String PLUGIN_NAME = "system-sdk-db";

    /**
     * 插件优先级（数值越小优先级越高）
     * DB 插件优先级高于 HTTP 插件
     */
    public static final int PLUGIN_PRIORITY = 10;

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
        log.info("正在初始化 {} 插件...", PLUGIN_NAME);
        // 无需初始化数据库连接，直接调用 ruoyi-system 模块中的 Service 层逻辑
        log.info("{} 插件初始化完成", PLUGIN_NAME);
    }

    @Override
    public void destroy() {
        log.info("正在销毁 {} 插件...", PLUGIN_NAME);
        // 无需清理数据库资源，ruoyi-system 模块已管理
        log.info("{} 插件已销毁", PLUGIN_NAME);
    }

    @Override
    public boolean isAvailable() {
        // 默认始终可用，实际业务调用由 ruoyi-system 模块处理
        return true;
    }
}
