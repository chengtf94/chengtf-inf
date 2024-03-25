package com.example.ctf.inf.ext.qw.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/25 23:31
 */
@Target(ElementType.TYPE)
public @interface PluginAnnotation {

    Integer tenantId();

    Integer bizTypeId();


}
