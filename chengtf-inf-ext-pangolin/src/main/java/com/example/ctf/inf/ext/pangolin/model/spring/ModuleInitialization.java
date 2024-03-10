package com.example.ctf.inf.ext.pangolin.model.spring;

import com.example.ctf.inf.ext.pangolin.model.Module;
import com.example.ctf.inf.ext.pangolin.model.ability.AbilityInvoker;
import com.example.ctf.inf.ext.pangolin.model.ext.ExtensionPointInvoker;
import com.example.ctf.inf.ext.pangolin.model.module.ModuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 模块初始化
 *
 * @author: chengtf
 * @date: 2023/10/29
 */
@Slf4j
@Component
public class ModuleInitialization implements InitializingBean, Ordered {

    @Resource
    private ExtensionPointInvoker extensionPointInvoker;

    @Resource
    private AbilityInvoker abilityInvoker;

    @Resource
    private ModuleManager moduleManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Module.init(extensionPointInvoker, abilityInvoker, moduleManager);
        log.info("Module初始化完成.");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
