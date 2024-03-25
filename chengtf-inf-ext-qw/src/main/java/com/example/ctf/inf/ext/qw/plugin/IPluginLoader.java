package com.example.ctf.inf.ext.qw.plugin;

/**
 * @description: 扩展点加载器接口
 * @author: chengtf
 * @date: 2024/3/25 22:13
 */
public interface IPluginLoader {

    /**
     * 加载业务自定义扩展点
     * @param pluginInterface 业务自定义扩展点接口
     * @param identifier 身份标识
     * @return 业务自定义扩展点实现
     * @param <T> 扩展点接口
     */
    <T extends IPlugin> T load(Class<T> pluginInterface, PluginIdentifier identifier);

}
