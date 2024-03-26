package com.example.ctf.inf.ext.qw.plugin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/25 23:25
 */
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static  <T> Map<String, T> getBeansOfType (Class<T> clazz) {
       return applicationContext.getBeansOfType(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

}
