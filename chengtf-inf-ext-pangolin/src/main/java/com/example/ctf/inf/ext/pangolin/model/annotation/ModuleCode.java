package com.example.ctf.inf.ext.pangolin.model.annotation;

import java.lang.annotation.*;

/**
 * 模块码：用于定义标记模块实例或实现
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ModuleCode {

    /**
     * 模块编码：由业务自定义区分
     */
    String value() default "";

    /**
     * 名称
     */
    String name() default "";

    /**
     * 描述
     */
    String desc() default "";

}
