package com.example.ctf.inf.ext.pangolin.model.annotation;

import java.lang.annotation.*;

/**
 * 能力码：用于定义标记能力实例或实现，形式上为算法步骤
 * 能力：传统面向过程编程的流程节点、算法步骤，在封装之后可能实现不同，类似于模板方法模式中子类重定义算法步骤的思想，在开发视图上不限分层，例如领域层、业务层都可以有能力
 * 同一个能力的多个实现使用相同的编码、不同的名称与描述 ？？？
 * 举个栗子：C端导购有2个店内组件分别是超级爆款场景卡、店铺头部标签面板，在链路上一个是搜推召回 -> 品补全（商品中台、营销中台等等），另一个是营销召回 + 会员权益召回等，
 * 在概念上都是组件召回，但是在实现流程节点处理上完全不同，这时候可以提取扩展点，在不同的产品实例上使用不同的召回能力。
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ModuleCode
public @interface AbilityCode {

    /**
     * 能力编码：由业务自定义区分
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
