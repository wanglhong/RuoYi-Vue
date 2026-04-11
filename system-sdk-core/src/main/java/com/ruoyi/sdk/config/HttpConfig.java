package com.ruoyi.sdk.config;

import com.ruoyi.common.exception.base.BaseException;

import java.util.HashMap;
import java.util.Map;

/**
 * Http 配置类
 *
 * @author wlih
 */
public class HttpConfig {

    /**
     * 基础URL
     */
    private String baseUrl;
    /**
     * 请求头
     */
    private Map<String, String> headers;

    /**
     * 全局配置
     */
    private static HttpConfig globalHttpConfig;
    /**
     * 临时配置
     */
    private static final ThreadLocal<HttpConfig> TMP_HTTP_CONFIG = new ThreadLocal<>();

    static {
        globalHttpConfig = new HttpConfig();
    }

    /**
     * 获取基础URL
     *
     * @return 基础URL
     */
    public static String getBaseUrl() {
        HttpConfig httpConfig = TMP_HTTP_CONFIG.get();
        if (httpConfig == null) {
            httpConfig = globalHttpConfig;
        }
        return httpConfig.baseUrl;
    }

    /**
     * 获取请求头
     * <p>
     * 变更该返回的对象不会影响原先的配置
     *
     * @return 请求头
     */
    public static Map<String, String> getHeaders() {
        HttpConfig httpConfig = TMP_HTTP_CONFIG.get();
        if (httpConfig == null) {
            httpConfig = globalHttpConfig;
        }
        return new HashMap<>(httpConfig.headers);
    }

    /**
     * 构建HttpConfig
     *
     * @return 默认 HttpConfig（http://localhost:8080）
     */
    public static HttpConfig builder() {
        return new HttpConfig();
    }

    /**
     * 设置基础URL
     *
     * @param baseUrl 基础URL
     * @return HttpConfig
     */
    public HttpConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * 添加请求头
     *
     * @param key   key
     * @param value value
     * @return HttpConfig
     */
    public HttpConfig addHeaders(String key, String value) {
        if (this.headers == null) {
            this.headers = new HashMap<>();
        }
        this.headers.put(key, value);
        return this;
    }

    /**
     * 设置全局配置
     *
     * @param httpConfig HttpConfig
     */
    public static void setGlobalHttpConfig(HttpConfig httpConfig) {
        configValidate(httpConfig);
        globalHttpConfig = httpConfig;
    }

    /**
     * 设置临时配置
     * <p>
     * 临时配置优先级高于全局配置，使用完毕后 请调用 clearTmpHttpConfig() 方法清除临时配置
     *
     * @param httpConfig HttpConfig
     */
    public static void setTmpHttpConfig(HttpConfig httpConfig) {
        configValidate(httpConfig);
        TMP_HTTP_CONFIG.set(httpConfig);
    }

    /**
     * 清空临时配置
     */
    public static void clearTmpHttpConfig() {
        TMP_HTTP_CONFIG.remove();
    }

    /**
     * 配置校验
     */
    public static void configValidate(HttpConfig httpConfig) {
        if (httpConfig == null) {
            throw new BaseException("HttpConfig cannot be null");
        }
        if (httpConfig.baseUrl == null || httpConfig.baseUrl.isEmpty()) {
            throw new BaseException("HttpConfig.baseUrl cannot be empty");
        }
    }

    /**
     * 构造函数
     */
    private HttpConfig() {
        this.baseUrl = "http://localhost:8080";
        this.headers = new HashMap<>();
    }

}
