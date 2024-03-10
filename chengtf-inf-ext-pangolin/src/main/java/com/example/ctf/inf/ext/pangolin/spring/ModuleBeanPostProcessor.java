package com.example.ctf.inf.ext.pangolin.spring;

import com.example.ctf.inf.ext.pangolin.annotation.*;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPoint;
import com.example.ctf.inf.ext.pangolin.ability.Ability;
import com.example.ctf.inf.ext.pangolin.module.spring.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.TypeUtils;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

/**
 * 模块Bean后置处理器，用于初始化添加模块的映射关系到模块存储容器ModuleRepository
 *
 * @author: chengtf
 * @date: 2023/10/29
 */
@Slf4j
@Component
public class ModuleBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // 获取Bean的类型，如果是代理类，则获取被代理类的类型
        Class<?> beanClass = bean.getClass();
        if (AopUtils.isAopProxy(bean)) {
            beanClass = AopUtils.getTargetClass(bean);
        }

        // 若不是扩展点接口、能力点接口，则直接返回
        if (!TypeUtils.isAssignable(ExtensionPoint.class, beanClass)
                || !TypeUtils.isAssignable(Ability.class, beanClass)) {
            return bean;
        }

        // 扩展点
        if (TypeUtils.isAssignable(ExtensionPoint.class, beanClass)) {
            String extCode = parseModuleCode(beanClass);
            if (null != extCode) {
                List<Class> definedClassList = findDefineClass(beanClass, BaseExtensionPoint.class);
                for (Class definedClass : definedClassList) {
                    ModuleRepository.putExtPointBeanName(definedClass, extCode, beanName);
                    log.info("初始化扩展点：{}，扩展点实现：{}", definedClass.getName(), beanName);
                }
            } else {
                log.error("初始化扩展点失败，扩展点实现：{}", beanName);
            }

        }

        // 能力点
        if (TypeUtils.isAssignable(Ability.class, beanClass)) {
            List<Class> definedClassList = findDefineClass(beanClass, BaseAbility.class);
            for (Class definedClass : definedClassList) {
                ModuleRepository.putAbilityBeanName(definedClass, beanName);
                log.info("初始化能力：{}，能力实现：{}", definedClass.getName(), beanName);
            }
        }

        return bean;
    }

    /**
     * 解析Bean实例类型上的模块编码
     *
     * @param beanClass Bean实例类型
     * @return Bean实例类型上的模块编码
     */
    private static String parseModuleCode(Class<?> beanClass) {
        String extCode = null;
        BusinessCode businessCode = AnnotationUtils.getAnnotation(beanClass, BusinessCode.class);
        if (null != businessCode) {
            extCode = businessCode.value();
        }

        if (null != extCode) {
            ProductCode productCode = AnnotationUtils.getAnnotation(beanClass, ProductCode.class);
            if (null != productCode) {
                extCode = productCode.value();
            }
        }

        if (null != extCode) {
            ModuleCode moduleCode = AnnotationUtils.getAnnotation(beanClass, ModuleCode.class);
            if (null != moduleCode) {
                extCode = moduleCode.value();
            }
        }

        return extCode;
    }

    /**
     * 递归查找定义的扩展点、能力点接口
     *
     * @param moduleClass     模块实现类
     * @param annotationClass 注解类
     * @return 扩展点原始接口
     */
    private static List<Class> findDefineClass(Class moduleClass, Class annotationClass) {
        List<Class> results = new LinkedList<>();
        if (null == moduleClass || null == annotationClass) {
            return results;
        }

        // 获取实现的接口数组
        Class[] interfaces = moduleClass.getInterfaces();

        // 若为空，则递归查找父类定义的扩展点、能力点接口
        if (null == interfaces || interfaces.length == 0) {
            Class superClass = moduleClass.getSuperclass();
            return findDefineClass(superClass, annotationClass);
        }

        // 否则，则按照注解类进行匹配
        for (Class interfaceClass : interfaces) {
            Annotation annotation = interfaceClass.getAnnotation(annotationClass);
            if (null != annotation) {
                results.add(interfaceClass);
            } else {
                results.addAll(findDefineClass(interfaceClass, annotationClass));
            }
        }

        return results;
    }

}
