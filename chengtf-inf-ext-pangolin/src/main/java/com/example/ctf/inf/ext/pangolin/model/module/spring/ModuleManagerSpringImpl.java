package com.example.ctf.inf.ext.pangolin.model.module.spring;

import com.example.ctf.inf.ext.pangolin.model.ModuleMatcher;
import com.example.ctf.inf.ext.pangolin.model.ability.Ability;
import com.example.ctf.inf.ext.pangolin.model.ext.ExtensionPoint;
import com.example.ctf.inf.ext.pangolin.model.module.ModuleManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 模块管理器实现：基于Spring
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Component
public class ModuleManagerSpringImpl implements ModuleManager, ApplicationContextAware {

    /**
     * Spring上下文对象
     */
    private ApplicationContext applicationContext;

    @Override
    public <Ext extends ExtensionPoint> List<Ext> getAllExtension(Class<Ext> extClass, List<String> moduleCodes) {
        if (null == extClass || CollectionUtils.isEmpty(moduleCodes)) {
            return new ArrayList<>(0);
        }

        // 获取扩展点实例Bean名称Map
        Multimap<String, String> extBeanNameMap;
        if ((extBeanNameMap = ModuleRepository.getExtPointBeanNameMap(extClass)) == null || extBeanNameMap.isEmpty()) {
            return new ArrayList<>(0);
        }

        // 解析扩展点实例Bean名称集合
        List<String> extBeanNames = Lists.newArrayList();
        for (String moduleCode : moduleCodes) {
            if (!extBeanNameMap.containsKey(moduleCode)) {
                continue;
            }
            Collection<String> beanNames = extBeanNameMap.get(moduleCode);
            if (CollectionUtils.isEmpty(beanNames)) {
                continue;
            }
            extBeanNames.addAll(beanNames);
        }

        // 解析扩展点实现
        List<Ext> allExtension = new ArrayList<>(extBeanNames.size());
        for (String extBeanName : extBeanNames) {
            allExtension.add(getBean(extBeanName, extClass));
        }

        return allExtension;
    }

    @Override
    public <A extends Ability> List<A> getAllAbility(Class<A> abilityClass) {
        if (null == abilityClass) {
            return new ArrayList<>(0);
        }

        // 获取能力点实例Bean名称列表
        Collection<String> abilityBeanNames = ModuleRepository.getAbilityBeanNames(abilityClass);
        if (CollectionUtils.isEmpty(abilityBeanNames)) {
            return new ArrayList<>(0);
        }

        // 解析能力点实现
        List<A> allAbility = new ArrayList<>(abilityBeanNames.size());
        for (String abilityBeanName : abilityBeanNames) {
            allAbility.add(getBean(abilityBeanName, abilityClass));
        }

        return allAbility;
    }

    @Override
    public List<ModuleMatcher> getAllModuleMatcher() {

        // 获取模块匹配器实例Bean列表
        List<ModuleMatcher> moduleMatchers = getBeans(ModuleMatcher.class);
        if (CollectionUtils.isEmpty(moduleMatchers)) {
            return new ArrayList<>(0);
        }

        // 按照优先级降序排序
        moduleMatchers.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
        return moduleMatchers;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public <T> List<T> getBeans(Class<T> requiredType) throws BeansException {
        Map<String, T> beansOfType = applicationContext.getBeansOfType(requiredType);
        if (MapUtils.isEmpty(beansOfType)) {
            return new ArrayList<>(0);
        }
        return Lists.newArrayList(beansOfType.values());
    }

}
