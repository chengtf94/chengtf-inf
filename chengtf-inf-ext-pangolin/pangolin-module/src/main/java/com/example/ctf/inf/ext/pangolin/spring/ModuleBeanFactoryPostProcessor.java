package com.example.ctf.inf.ext.pangolin.spring;

import com.example.ctf.inf.ext.pangolin.annotation.AbilityCode;
import com.example.ctf.inf.ext.pangolin.annotation.BusinessCode;
import com.example.ctf.inf.ext.pangolin.annotation.ModuleCode;
import com.example.ctf.inf.ext.pangolin.annotation.ProductCode;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 模块Bean工厂后置处理器，用于扫描模块实例作为Bean进行IoC注入
 *
 * @author: chengtf
 * @date: 2023/10/29
 */
//@Slf4j
@Component
public class ModuleBeanFactoryPostProcessor implements BeanFactoryPostProcessor, ApplicationContextAware {

    /**
     * 自定义模块Bean扫描器
     */
    static class ModuleScanner extends ClassPathBeanDefinitionScanner {

        public ModuleScanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        @Override
        public void registerDefaultFilters() {
            this.addIncludeFilter(new AnnotationTypeFilter(ModuleCode.class));
        }

        @Override
        public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return super.isCandidateComponent(beanDefinition)
                    && (beanDefinition.getMetadata().hasAnnotation(ModuleCode.class.getName())
                    || beanDefinition.getMetadata().hasAnnotation(ProductCode.class.getName())
                    || beanDefinition.getMetadata().hasAnnotation(BusinessCode.class.getName())
                    || beanDefinition.getMetadata().hasAnnotation(AbilityCode.class.getName())
            );
        }

    }

    /**
     * Spring上下文对象
     */
    private ApplicationContext applicationContext;

    /**
     * 默认框架模块包变量
     */
    private final static String PANGOLIN_MODULE_BASE_PACKAGES = "pangolin.module.basePackages";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ModuleScanner moduleScanner = new ModuleScanner((BeanDefinitionRegistry) beanFactory);
        moduleScanner.setResourceLoader(applicationContext);
        moduleScanner.setBeanNameGenerator(((definition, registry) -> definition.getBeanClassName()));
        String[] basePackages = getBasePackages();
        moduleScanner.scan(basePackages);
//        log.info("添加扫描路径：{}", JSON.toJSONString(basePackages));
    }

    /**
     * 获取模块包
     *
     * @return 模块包
     */
    private String[] getBasePackages() {

        // 优先级1：获取系统参数
        String basePackageString = System.getProperty(PANGOLIN_MODULE_BASE_PACKAGES);
        String[] basePackages = parseBasePackages(basePackageString);
        if (null != basePackages && basePackages.length != 0) {
            return basePackages;
        }

        // 优先级2：获取SpringBoot配置
        String profile = System.getProperty("spring.profiles.active");
        String fileName = null;
        if (org.springframework.util.StringUtils.hasText(profile)) {
            fileName = String.format("application-%s.properties", profile.trim());
        } else {
            fileName = "application.properties";
        }

        InputStream inputStream = null;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            Properties properties = new Properties();
            if (null != inputStream) {
                properties.load(inputStream);
                basePackageString = properties.getProperty(PANGOLIN_MODULE_BASE_PACKAGES);
                basePackages = parseBasePackages(basePackageString);
                if (null != basePackages && basePackages.length != 0) {
                    return basePackages;
                }
            }
        } catch (Exception e) {
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }

        // 优先级3：获取启动类所在package
        StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceArray) {
            if (!"main".equals(stackTraceElement.getMethodName())) {
                continue;
            }
            try {
                Class<?> aClass = Class.forName(stackTraceElement.getClassName());
                String packageName = aClass.getPackage().getName();
                return new String[]{packageName};
            } catch (ClassNotFoundException e) {

            }
        }

        return new String[0];
    }

    /**
     * 解析模块包字符串
     *
     * @param basePackageString 模块包字符串
     * @return 模块包数组
     */
    private String[] parseBasePackages(String basePackageString) {
        if (StringUtils.isBlank(basePackageString)) {
            return null;
        }
        return Lists.newArrayList(Splitter.on(",").split(basePackageString)).stream()
                .filter(StringUtils::isNoneBlank).map(String::trim)
                .filter(StringUtils::isNoneBlank).collect(Collectors.toList()).toArray(new String[]{});
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
