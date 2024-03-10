package com.example.ctf.inf.ext.pangolin.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展点：用于定义标记扩展点接口，不允许多个叠加
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BaseExtensionPoint {

}
