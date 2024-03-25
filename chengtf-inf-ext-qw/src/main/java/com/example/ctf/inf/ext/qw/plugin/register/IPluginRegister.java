package com.example.ctf.inf.ext.qw.plugin.register;

import com.example.ctf.inf.ext.qw.plugin.IPlugin;
import com.example.ctf.inf.ext.qw.plugin.PluginIdentifier;

/**
 * @description: 扩展点注册器接口
 * @author: chengtf
 * @date: 2024/3/25 23:13
 */
public interface IPluginRegister {

    /**
     * 注册扩展点
     * @param identifier 身份标识
     * @param plugin 业务自定义扩展点实现
     */
    void doRegistration(PluginIdentifier identifier, IPlugin plugin);
}
