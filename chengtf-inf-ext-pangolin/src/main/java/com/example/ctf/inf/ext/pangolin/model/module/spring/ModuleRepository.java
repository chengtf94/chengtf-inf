package com.example.ctf.inf.ext.pangolin.model.module.spring;

import com.example.ctf.inf.ext.pangolin.model.ability.Ability;
import com.example.ctf.inf.ext.pangolin.model.ext.ExtensionPoint;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模块存储容器：用于存储扩展点、模块编码、具体实现的映射关系
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public final class ModuleRepository {

    /**
     * 扩展点映射关系Map，外层key是扩展点接口Class名称，里层key为模块编码，里层value为该模块编码对应的扩展点实例Bean名称
     */
    private final static Map<String, Multimap<String, String>> EXT_CONTEXT = new ConcurrentHashMap<>();

    /**
     * 能力映射关系Map，key是能力接口Class名称，value为能力实例Bean名称
     */
    private final static Multimap<String, String> ABILITY_CONTEXT = ArrayListMultimap.create();

    /**
     * 获取扩展点实例Bean名称Map
     *
     * @param extClass 扩展点Class
     * @return 扩展点实例Bean名称Map，key为模块编码，value为该模块编码对应的扩展点实例Bean名称
     */
    public static Multimap<String, String> getExtPointBeanNameMap(Class<? extends ExtensionPoint> extClass) {
        return EXT_CONTEXT.get(extClass.getName());
    }

    /**
     * 设置扩展点实例Bean名称
     *
     * @param extClass   扩展点Class
     * @param moduleCode 模块编码
     * @param beanName   扩展点实例Bean名称
     */
    public static void putExtPointBeanName(Class<? extends ExtensionPoint> extClass, String moduleCode, String beanName) {
        Multimap<String, String> extPointBeanNameMap = getExtPointBeanNameMap(extClass);
        if (null == extPointBeanNameMap) {
            extPointBeanNameMap = ArrayListMultimap.create();
            EXT_CONTEXT.put(extClass.getName(), extPointBeanNameMap);
        }
        extPointBeanNameMap.put(moduleCode, beanName);
    }

    /**
     * 获取能力点实例Bean名称列表
     *
     * @param abilityClass 能力点Class
     * @return 能力点实例Bean名称列表
     */
    public static Collection<String> getAbilityBeanNames(Class<? extends Ability> abilityClass) {
        return ABILITY_CONTEXT.get(abilityClass.getName());
    }


    /**
     * 设置能力点实例Bean名称
     *
     * @param abilityClass 能力点Class
     * @param beanName     能力点实例Bean名称
     */
    public static void putAbilityBeanName(Class<? extends Ability> abilityClass, String beanName) {
        ABILITY_CONTEXT.put(abilityClass.getName(), beanName);
    }

}
