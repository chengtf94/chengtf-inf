package com.example.ctf.inf.ext.qw.plugin.register;

import com.example.ctf.inf.ext.qw.plugin.IPlugin;
import com.example.ctf.inf.ext.qw.plugin.PluginIdentifier;
import com.example.ctf.inf.ext.qw.plugin.repository.IPluginRepository;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description: 扩展点注册器实现
 * @author: chengtf
 * @date: 2024/3/25 23:15
 */
@Component
public class PluginRegisterImpl implements IPluginRegister {

    @Autowired
    private IPluginRepository pluginRepository;

    @Override
    public void doRegistration(PluginIdentifier identifier, IPlugin plugin) {
        // TODO: 业务打点
        Preconditions.checkArgument(Objects.nonNull(identifier));
        identifier.check();
        Preconditions.checkArgument(Objects.nonNull(plugin));
        for (Class anInterface : plugin.getClass().getInterfaces()) {
            if (!anInterface.equals(IPlugin.class) && IPlugin.class.isAssignableFrom(anInterface)) {
                pluginRepository.store(anInterface, identifier, plugin);
            }
        }
    }

}
