package com.example.ctf.inf.ext.pangolin.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: pangolin自动配置类
 * @author: chengtf
 * @date: 2024/3/11 0:32
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan({"com.example.ctf.inf.ext.pangolin"})
public class PangolinModuleAutoConfiguration {
}
