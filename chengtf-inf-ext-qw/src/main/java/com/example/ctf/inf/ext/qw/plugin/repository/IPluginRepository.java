package com.example.ctf.inf.ext.qw.plugin.repository;

import com.example.ctf.inf.ext.qw.plugin.IPlugin;
import com.example.ctf.inf.ext.qw.plugin.PluginIdentifier;

/**
 * @description: 扩展点存储器接口
 * @author: chengtf
 * @date: 2024/3/25 22:20
 */
public interface IPluginRepository {

    /**
     * 存储业务自定义扩展点
     * @param pluginInterface 业务自定义扩展点接口
     * @param identifier 身份标识
     * @param plugin 业务自定义扩展点实现
     * @param <T> 扩展点接口
     */
    <T extends IPlugin> void store(Class<T> pluginInterface, PluginIdentifier identifier, IPlugin plugin);

    /**
     * 加载业务自定义扩展点
     * @param pluginInterface 业务自定义扩展点接口
     * @param identifier 身份标识
     * @return 业务自定义扩展点实现
     * @param <T> 扩展点接口
     */
    <T extends IPlugin> T load(Class<T> pluginInterface, PluginIdentifier identifier);

    /**
     * 判断某个业务自定义扩展点接口是否加载过
     * @param pluginInterface 业务自定义扩展点接口
     * @return 是否加载过
     * @param <T> 扩展点接口
     */
    <T extends IPlugin> Boolean loaded(Class<T> pluginInterface);


}
