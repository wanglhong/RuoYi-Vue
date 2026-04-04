package com.ruoyi.sdk.plugin;

/**
 * SDK 插件 SPI 接口
 * <p>
 * 所有 SDK 插件实现都需要实现此接口，通过 Java SPI 机制进行加载
 * </p>
 *
 * @author wlih
 */
public interface SdkPlugin {

    /**
     * 获取插件名称
     *
     * @return 插件名称
     */
    String getName();

    /**
     * 获取插件优先级
     * <p>
     * 数值越小优先级越高，当多个插件同时存在时，优先使用高优先级的插件
     * </p>
     *
     * @return 优先级数值
     */
    int getPriority();

    /**
     * 插件初始化方法
     * <p>
     * 在插件加载时调用，用于初始化资源
     * </p>
     */
    void initialize();

    /**
     * 插件销毁方法
     * <p>
     * 在应用关闭时调用，用于释放资源
     * </p>
     */
    void destroy();

    /**
     * 插件是否可用
     *
     * @return true 表示可用，false 表示不可用
     */
    default boolean isAvailable() {
        return true;
    }
}
