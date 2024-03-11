package com.example.ctf.inf.ext.pangolin.annotation;

import java.lang.annotation.*;

/**
 * 产品码：用于定义标记产品实例或实现，形式上是横向模块。
 * 产品：是一组为实现同一个产品诉求的扩展集合，产品包将一系列扩展点在逻辑上聚合起来。
 * 举个栗子：C端导购有2个店内场景卡组件分别是超级爆款、爆品N选M，在链路上都是搜推召回 -> 品补全（商品中台、营销中台等等），但是2个组件的过滤、排序、截断等逻辑不同，
 * 这时候可以提取扩展点，将2个组件定义为2个不同的产品，然后在产品实例上实现各个扩展点。
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ModuleCode
public @interface ProductCode {

    /**
     * 产品编码：由业务自定义区分
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
