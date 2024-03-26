package com.example.ctf.inf.ext.qw.plugin.repository;

import com.example.ctf.inf.ext.qw.plugin.IPlugin;
import com.example.ctf.inf.ext.qw.plugin.PluginIdentifier;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 扩展点存储器实现
 * @author: chengtf
 * @date: 2024/3/25 22:23
 */
@Component
public class PluginRepositoryImpl implements IPluginRepository {

    /**
     * 扩展点存储器
     * key：扩展点接口
     * value：key为业务身份，value为扩展点实现
     */
    private Map<Class<? extends IPlugin>, Map<String, IPlugin>> pluginInterface2Extension = new ConcurrentHashMap<>();


    @Override
    public <T extends IPlugin> void store(Class<T> pluginInterface, PluginIdentifier identifier, IPlugin plugin) {
        // TODO: 业务打点
        Preconditions.checkArgument(Objects.nonNull(pluginInterface));
        Preconditions.checkArgument(Objects.nonNull(identifier));
        identifier.check();
        Preconditions.checkArgument(Objects.nonNull(plugin));
        Map<String, IPlugin> key2Extension = pluginInterface2Extension.computeIfAbsent(pluginInterface, key -> new ConcurrentHashMap<>());
        key2Extension.put(identifier.buildKey(), plugin);
    }

    @Override
    public <T extends IPlugin> T load(Class<T> pluginInterface, PluginIdentifier identifier) {
        Preconditions.checkArgument(Objects.nonNull(pluginInterface));
        Preconditions.checkArgument(Objects.nonNull(identifier));
        identifier.check();
        Map<String, IPlugin> key2Extension = pluginInterface2Extension.get(pluginInterface);
        return MapUtils.isNotEmpty(key2Extension) ? (T) key2Extension.get(identifier.buildKey()) : null;
    }

    @Override
    public <T extends IPlugin> Boolean loaded(Class<T> pluginInterface) {
        return pluginInterface2Extension.containsKey(pluginInterface);
    }
}
