package com.example.ctf.inf.ext.qw.plugin;

import com.example.ctf.inf.ext.qw.plugin.util.ApplicationContextUtil;
import com.example.ctf.inf.ext.qw.plugin.register.IPluginRegister;
import com.example.ctf.inf.ext.qw.plugin.repository.IPluginRepository;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @description: 扩展点加载器实现
 * @author: chengtf
 * @date: 2024/3/25 22:17
 */
@Component
public class PluginLoaderImpl implements IPluginLoader {

    @Autowired
    private IPluginRegister pluginRegister;

    @Autowired
    private IPluginRepository pluginRepository;

    @Override
    public <T extends IPlugin> T load(Class<T> pluginInterface, PluginIdentifier identifier) {
        // TODO：业务打点
        Preconditions.checkArgument(Objects.nonNull(pluginInterface));
        Preconditions.checkArgument(Objects.nonNull(identifier));
        identifier.check();
        if (pluginRepository.loaded(pluginInterface)) {
            return pluginRepository.load(pluginInterface, identifier);
        }
        synchronized (PluginLoaderImpl.class) {
            if (pluginRepository.loaded(pluginInterface)) {
                return pluginRepository.load(pluginInterface, identifier);
            }
            return loadFromSpringAndRegister(pluginInterface, identifier);
        }
    }

    /**
     * 从Spring框架中扫描扩展点，并注册扩展点
     * @param pluginInterface 业务自定义扩展点接口
     * @param identifier 身份标识
     * @return 业务自定义扩展点实现
     * @param <T> 扩展点接口
     */
    private <T extends IPlugin> T loadFromSpringAndRegister(Class<T> pluginInterface, PluginIdentifier identifier) {

        Map<String, T> beanName2Beans = ApplicationContextUtil.getBeansOfType(pluginInterface);
        if (MapUtils.isEmpty(beanName2Beans)) {
            return null;
        }
        T finalExtension = null;
        for (T extension : beanName2Beans.values()) {
            PluginIdentifier pluginIdentifier = extractPluginIdentifier(extension);
            if (Objects.isNull(pluginIdentifier)) {
                // TODO：日志埋点
                continue;
            }
            pluginRegister.doRegistration(pluginIdentifier, extension);
            if (pluginIdentifier.equals(identifier)) {
                finalExtension = extension;
            }
        }
        return finalExtension;
    }

    private PluginIdentifier extractPluginIdentifier(IPlugin plugin) {
        if (Objects.isNull(plugin)) {
            return null;
        }
        PluginAnnotation annotation = plugin.getClass().getAnnotation(PluginAnnotation.class);
        return Objects.nonNull(annotation) ? new PluginIdentifier(annotation.tenantId(), annotation.bizTypeId()) : null;
    }

}
