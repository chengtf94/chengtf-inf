package com.example.ctf.inf.ext.qw.plugin;

import org.springframework.stereotype.Component;

/**
 * @description: 扩展点加载器实现
 * @author: chengtf
 * @date: 2024/3/25 22:17
 */
@Component
public class PluginLoaderImpl implements IPluginLoader {

    @Override
    public <T extends IPlugin> T load(Class<T> pluginInterface, PluginIdentifier identifier) {
        return null;
    }

}
