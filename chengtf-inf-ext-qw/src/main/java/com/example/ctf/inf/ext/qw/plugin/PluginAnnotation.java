package com.example.ctf.inf.ext.qw.plugin;

import java.lang.annotation.*;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/25 23:31
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PluginAnnotation {

    int tenantId() default 0;

    int bizTypeId() default 0;

}
