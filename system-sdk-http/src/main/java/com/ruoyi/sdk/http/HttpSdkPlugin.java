package com.ruoyi.sdk.http;

import com.ruoyi.sdk.plugin.SdkPlugin;

import java.net.http.HttpClient;
import java.util.logging.Logger;

/**
 * HTTP SDK 插件实现
 * <p>
 * 提供基于 HTTP 的 SDK 功能实现
 * 优先级：20（低于 DB 插件）
 * </p>
 *
 * @author wlih
 */
public class HttpSdkPlugin implements SdkPlugin {

    private static final Logger log = Logger.getLogger(HttpSdkPlugin.class.getName());

    /**
     * 插件名称
     */
    public static final String PLUGIN_NAME = "system-sdk-http";

    /**
     * 插件优先级（数值越小优先级越高）
     * HTTP 插件优先级低于 DB 插件
     */
    public static final int PLUGIN_PRIORITY = 20;

    private HttpClient httpClient;

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
        log.info("正在初始化 " + PLUGIN_NAME + " 插件...");
        
        // 初始化 HTTP 客户端相关资源
        if (httpClient == null) {
            this.httpClient = HttpClient.newHttpClient();
            log.info(PLUGIN_NAME + " 插件：已创建默认 HttpClient");
        }
        
        log.info(PLUGIN_NAME + " 插件初始化完成");
    }

    @Override
    public void destroy() {
        log.info("正在销毁 " + PLUGIN_NAME + " 插件...");
        // HttpClient 不需要显式销毁
        this.httpClient = null;
        log.info(PLUGIN_NAME + " 插件已销毁");
    }

    @Override
    public boolean isAvailable() {
        // HTTP 插件始终可用
        return true;
    }

    /**
     * 获取 HttpClient
     *
     * @return HttpClient
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }
}
