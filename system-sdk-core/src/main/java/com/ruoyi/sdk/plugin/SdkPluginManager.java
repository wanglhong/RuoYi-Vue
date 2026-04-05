package com.ruoyi.sdk.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * SDK 插件管理器
 * <p>
 * 负责通过 Java SPI 机制加载和管理所有可用的 SDK 插件
 * 支持优先级排序，优先使用高优先级的插件
 * </p>
 *
 * @author wlih
 */
public class SdkPluginManager {

    private static final Logger log = Logger.getLogger(SdkPluginManager.class.getName());

    /**
     * 当前激活的主插件
     */
    private SdkPlugin activePlugin;

    /**
     * 所有已加载的插件列表
     */
    private final List<SdkPlugin> loadedPlugins = new ArrayList<>();

    /**
     * 构造函数，通过 SPI 加载所有插件
     */
    public SdkPluginManager() {
        loadPlugins();
    }

    /**
     * 通过 SPI 加载所有可用的插件
     */
    private void loadPlugins() {
        // 使用线程上下文类加载器，确保能加载到所有依赖中的插件实现
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ServiceLoader<SdkPlugin> serviceLoader = ServiceLoader.load(SdkPlugin.class, classLoader);
        for (SdkPlugin plugin : serviceLoader) {
            try {
                plugin.initialize();
                loadedPlugins.add(plugin);
                log.info("成功加载 SDK 插件：" + plugin.getName() + "，优先级：" + plugin.getPriority());
            } catch (Exception e) {
                log.severe("加载 SDK 插件失败：" + plugin.getName() + ", 错误：" + e.getMessage());
            }
        }
        // 按优先级排序（数值越小优先级越高）
        loadedPlugins.sort((p1, p2) -> Integer.compare(p1.getPriority(), p2.getPriority()));
        // 选择优先级最高的可用插件作为主插件
        selectActivePlugin();
    }

    /**
     * 选择当前激活的主插件
     * <p>
     * 从已加载的插件中选择优先级最高的可用插件
     * </p>
     */
    private void selectActivePlugin() {
        if (loadedPlugins.isEmpty()) {
//            log.warning("未找到任何可用的 SDK 插件");
//            this.activePlugin = null;
//            return;
            throw new IllegalStateException("未找到任何可用的 SDK 插件");
        }
        // 找到第一个可用的插件（因为已经按优先级排序）
        this.activePlugin = loadedPlugins.stream()
                .filter(SdkPlugin::isAvailable)
                .findFirst()
                .orElse(null);
        if (this.activePlugin != null) {
            log.info("已激活主插件：" + this.activePlugin.getName() + "，优先级：" + this.activePlugin.getPriority());
        } else {
            log.warning("所有插件都不可用");
        }
    }

    /**
     * 获取当前激活的主插件
     * <p>
     * 如果同时存在多个插件，返回优先级最高的那个
     * 按照优先级规则：system-sdk-db > system-sdk-http
     * </p>
     *
     * @return 当前激活的插件，如果没有可用插件则返回 null
     */
    public SdkPlugin getActivePlugin() {
        return activePlugin;
    }

    /**
     * 获取所有已加载的插件列表
     *
     * @return 插件列表
     */
    public List<SdkPlugin> getAllPlugins() {
        return new ArrayList<>(loadedPlugins);
    }

    /**
     * 根据名称获取指定插件
     *
     * @param name 插件名称
     * @return 指定的插件，如果不存在则返回 null
     */
    public SdkPlugin getPluginByName(String name) {
        return loadedPlugins.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * 检查是否存在指定类型的插件
     *
     * @param pluginClass 插件类型
     * @param <T>         插件类型参数
     * @return true 如果存在该类型的插件
     */
    public <T extends SdkPlugin> boolean hasPlugin(Class<T> pluginClass) {
        return loadedPlugins.stream()
                .anyMatch(pluginClass::isInstance);
    }

    /**
     * 获取指定类型的插件
     *
     * @param pluginClass 插件类型
     * @param <T>         插件类型参数
     * @return 指定类型的插件，如果不存在则返回 null
     */
    public <T extends SdkPlugin> T getPluginByType(Class<T> pluginClass) {
        return loadedPlugins.stream()
                .filter(pluginClass::isInstance)
                .map(pluginClass::cast)
                .findFirst()
                .orElse(null);
    }

    /**
     * 销毁所有插件
     */
    public void destroyAll() {
        for (SdkPlugin plugin : loadedPlugins) {
            try {
                plugin.destroy();
                log.info("已销毁插件：" + plugin.getName());
            } catch (Exception e) {
                log.severe("销毁插件失败：" + plugin.getName() + ", 错误：" + e.getMessage());
            }
        }
        loadedPlugins.clear();
        this.activePlugin = null;
    }

}
