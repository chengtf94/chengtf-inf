package com.example.ctf.inf.ext.pangolin.annotation;

import java.lang.annotation.*;

/**
 * 业务码：用于定义标记纵向业务实例或实现，形式上是纵向模块。
 * 垂直业务：是一组为实现同一个垂直业务诉求的扩展集合，业务包将一系列扩展点在逻辑上聚合起来。
 * 举个栗子：C端导购在普通店铺、品牌官旗店铺都有超级爆款这个店内场景卡组件，在链路上都是搜推召回 -> 品补全（商品中台、营销中台等等），但是2种店铺的过滤、排序、截断等逻辑不同，
 * 这时候可以提取扩展点，在原产品码的基础上，叠加1层业务码，优先识别业务扩展点实现。
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ModuleCode
public @interface BusinessCode {

    /**
     * 业务编码：由业务自定义区分
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
