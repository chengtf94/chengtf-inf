package com.example.ctf.inf.ext.pangolin.model.module;

import com.example.ctf.inf.ext.pangolin.model.ModuleMatcher;
import com.example.ctf.inf.ext.pangolin.model.ability.Ability;
import com.example.ctf.inf.ext.pangolin.model.ext.ExtensionPoint;

import java.util.List;

/**
 * 模块管理器接口
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public interface ModuleManager {

    /**
     * 获取所有的扩展点实现
     *
     * @param extClass    扩展点类型
     * @param moduleCodes 模块编码列表
     * @param <Ext>       扩展点类型定义
     * @return 所有的扩展点实现
     */
    <Ext extends ExtensionPoint> List<Ext> getAllExtension(Class<Ext> extClass, List<String> moduleCodes);


    /**
     * 获取所有的能力点实现
     *
     * @param abilityClass 能力点类型
     * @param <A>          能力点类型定义
     */
    <A extends Ability> List<A> getAllAbility(Class<A> abilityClass);


    /**
     * 获取所有的模块匹配器
     *
     * @return 所有的模块匹配器
     */
    List<ModuleMatcher> getAllModuleMatcher();

}
