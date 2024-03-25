package com.example.ctf.inf.ext.qw.plugin;

import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/25 23:25
 */
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;

    static {
        applicationContext = null;
    }


    public static  <T> Map<String, T> getBeansOfType (Class<T> clazz) {
       return applicationContext.getBeansOfType(clazz);
    }



}
