package com.example.ctf.inf.ext.pangolin;

import com.example.ctf.inf.ext.pangolin.ability.Ability;
import com.example.ctf.inf.ext.pangolin.ability.AbilityInvoker;
import com.example.ctf.inf.ext.pangolin.annotation.BusinessCode;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPoint;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPointCallback;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPointInvoker;
import com.example.ctf.inf.ext.pangolin.model.ModuleSession;
import com.example.ctf.inf.ext.pangolin.module.ModuleManager;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 统一封装门面工具类，用于统一管理内部模块，向外提供服务
 * @author: chengtf
 * @date: 2023/10/28
 * @version: 1.0
 */
@Slf4j
public final class Module {

    private Module() {
    }

    /**
     * 初始化模块编码
     *
     * @param moduleSession 模块上下文参数
     */
    public static void initModuleCode(ModuleSession moduleSession) {
        if (null == moduleSession) {
            return;
        }
        List<String> moduleCodes = matchModuleCodes(moduleSession);
        if (CollectionUtils.isEmpty(moduleCodes)) {
            log.warn("未匹配到扩展模块，param={}", moduleSession);
        }
        // 空模块也进行初始化，避免重复匹配
        moduleSession.setMatchedModuleCodes(moduleCodes);
    }

    /**
     * 获取所有的扩展点实现
     *
     * @param extClass 扩展点类型
     * @param session  业务对象
     * @param <Ext>    扩展点类型定义
     * @param <T>      业务对象类型定义
     * @return 所有的扩展点实现
     */
    public static <Ext extends ExtensionPoint, T extends ModuleSession> List<Ext> getAllExtension(Class<Ext> extClass, T session) {
        return getExtPointInvoker().getAllExtension(extClass, session);
    }

    /**
     * 执行第一个满足条件、回调执行结果非空的扩展点
     *
     * @param extClass 扩展点类型
     * @param session  业务对象
     * @param callback 扩展点回调接口
     * @param <Ext>    扩展点类型定义
     * @param <T>      业务对象类型定义
     * @param <R>      扩展点回调执行结果类型
     * @return 扩展点回调执行结果
     */
    public static <Ext extends ExtensionPoint, T extends ModuleSession, R> R executeFirstNotNull(Class<Ext> extClass, T session, ExtensionPointCallback<Ext, R> callback) {
        return getExtPointInvoker().executeFirstNotNull(extClass, session, callback);
    }

    /**
     * 执行所有扩展点
     *
     * @param extClass 扩展点类型
     * @param session  业务对象
     * @param callback 扩展点回调接口
     * @param <Ext>    扩展点类型定义
     * @param <T>      业务对象类型定义
     * @param <R>      扩展点回调执行结果类型
     * @return 扩展点回调执行结果列表
     */
    public static <Ext extends ExtensionPoint, T extends ModuleSession, R> List<R> executeAll(Class<Ext> extClass, T session, ExtensionPointCallback<Ext, R> callback) {
        return getExtPointInvoker().executeAll(extClass, session, callback);
    }

    /**
     * 获取所有的能力点实现
     *
     * @param abilityClass 能力点类型
     * @param param        业务对象
     * @param <A>          能力点类型定义
     * @param <P>          业务对象类型定义
     * @return 所有的能力点实现
     */
    public static <A extends Ability, P> List<A> getAllAbility(Class<A> abilityClass, P param) {
        return getAbilityInvoker().getAllAbility(abilityClass, param);
    }

    /**
     * 获取第一个能力点实现
     *
     * @param abilityClass 能力点类型
     * @param param        业务对象
     * @param <A>          能力点类型定义
     * @param <P>          业务对象类型定义
     * @return 第一个能力点实现
     */
    public static <A extends Ability, P> A getFirstAbility(Class<A> abilityClass, P param) {
        List<A> allAbility = getAbilityInvoker().getAllAbility(abilityClass, param);
        return CollectionUtils.isNotEmpty(allAbility) ? allAbility.get(0) : null;
    }

    /**
     * 匹配模块编码
     *
     * @param moduleSession 模块上下文参数
     * @return 匹配的模块编码列表
     */
    private static List<String> matchModuleCodes(ModuleSession moduleSession) {
        List<String> matchModuleCodes = new ArrayList<>();
        if (null == moduleSession) {
            return matchModuleCodes;
        }

        // 获取所有的模块匹配器
        List<ModuleMatcher> allModuleMatcher = getModuleManager().getAllModuleMatcher();
        if (CollectionUtils.isEmpty(allModuleMatcher)) {
            return matchModuleCodes;
        }

        // 遍历判断是否为垂直业务编码，从而判断放入多选一、或可叠加的列表中
        List<ModuleMatcher> uniqueMatcher = new ArrayList<>();
        List<ModuleMatcher> multiMatcher = new ArrayList<>();
        for (ModuleMatcher moduleMatcher : allModuleMatcher) {
            Optional<BusinessCode> businessCode = getBusinessCode(moduleMatcher.getClass());
            if (businessCode.isPresent()) {
                uniqueMatcher.add(moduleMatcher);
            } else {
                multiMatcher.add(moduleMatcher);
            }
        }

        // 多选一的模块优先匹配，而且垂直业务只能有1个
        for (ModuleMatcher moduleMatcher : uniqueMatcher) {
            if (isMatch(moduleMatcher, moduleSession)) {
                matchModuleCodes.add(moduleMatcher.getModuleCode());
                break;
            }
        }

        // 可叠加的模块后置匹配，横向产品业务可以有多个
        for (ModuleMatcher moduleMatcher : multiMatcher) {
            if (isMatch(moduleMatcher, moduleSession)) {
                matchModuleCodes.add(moduleMatcher.getModuleCode());
                break;
            }
        }

        return matchModuleCodes;
    }

    /**
     * 判断是否匹配
     *
     * @param moduleMatcher 模块匹配器
     * @param moduleSession 模块上下文参数
     * @return 是否匹配
     */
    private static boolean isMatch(ModuleMatcher moduleMatcher, ModuleSession moduleSession) {
        try {
            Optional<Method> methodOptional = getIsMatch(moduleSession.getClass());
            Type genericInterface = moduleMatcher.getClass().getGenericInterfaces()[0];
            if (!(genericInterface instanceof ParameterizedType)) {
                throw new RuntimeException("ModuleMatcher实现类泛型类型未指定，例如："
                        + moduleMatcher.getClass().getName()
                        + " implements ModuleMatcher<需要指定类型>");
            }
            if (!methodOptional.isPresent()
                    || !Class.forName(((ParameterizedType) genericInterface).getActualTypeArguments()[0].getTypeName()).isInstance(moduleSession)) {
                return false;
            }
            Object isMatch = methodOptional.get().invoke(moduleMatcher, moduleSession);
            if (isMatch instanceof Boolean && (Boolean) isMatch) {
                return true;
            }
        } catch (Exception e) {
            log.error("isMatch error, message: " + e.getMessage());
        }
        return false;
    }

    private final static Map<String, Optional<Method>> IS_MATCH_METHOD_CACHE = new ConcurrentHashMap<>();
    private final static String IS_MATCH = "isMatch";

    /**
     * 根据模块匹配器类型获取匹配方法
     *
     * @param matcherClass 模块匹配器类型
     * @return 匹配方法
     */
    private static Optional<Method> getIsMatch(Class<? extends ModuleSession> matcherClass) {
        if (null == matcherClass) {
            return Optional.empty();
        }
        String cacheKey = Joiner.on(",").join(matcherClass.getName(), IS_MATCH);
        Optional<Method> methodOptional = IS_MATCH_METHOD_CACHE.get(cacheKey);
        if (null == methodOptional) {
            Method method = null;
            try {
                method = matcherClass.getDeclaredMethod(IS_MATCH, ModuleSession.class);
            } catch (Exception e) {
                log.warn("getIsMatch error, paramClass: " + ModuleSession.class + " matcher: " + matcherClass);
            }
            methodOptional = null != method ? Optional.of(method) : Optional.empty();
            IS_MATCH_METHOD_CACHE.put(cacheKey, methodOptional);
        }
        return methodOptional;
    }


    private final static Map<Class, Optional<BusinessCode>> BUSINESS_CODE_CACHE = new ConcurrentHashMap<>();

    /**
     * 根据模块匹配器类型获取业务编码
     *
     * @param matcherClass 模块匹配器类型
     * @return 业务编码
     */
    private static Optional<BusinessCode> getBusinessCode(Class<? extends ModuleMatcher> matcherClass) {
        if (null == matcherClass) {
            return Optional.empty();
        }
        Optional<BusinessCode> businessCodeOptional = BUSINESS_CODE_CACHE.get(matcherClass);
        if (null == businessCodeOptional) {
            BusinessCode annotation = AnnotationUtils.getAnnotation(matcherClass, BusinessCode.class);
            businessCodeOptional = null != annotation ? Optional.of(annotation) : Optional.empty();
            BUSINESS_CODE_CACHE.put(matcherClass, businessCodeOptional);
        }
        return businessCodeOptional;
    }


    private static ExtensionPointInvoker EXT_POINT_INVOKER;
    private static AbilityInvoker ABILITY_INVOKER;
    private static ModuleManager MODULE_MANAGER;

    public static void init(ExtensionPointInvoker extensionPointInvoker, AbilityInvoker abilityInvoker, ModuleManager moduleManager) {
        EXT_POINT_INVOKER = extensionPointInvoker;
        ABILITY_INVOKER = abilityInvoker;
        MODULE_MANAGER = moduleManager;
    }

    public static ExtensionPointInvoker getExtPointInvoker() {
        return EXT_POINT_INVOKER;
    }

    public static AbilityInvoker getAbilityInvoker() {
        return ABILITY_INVOKER;
    }

    public static ModuleManager getModuleManager() {
        return MODULE_MANAGER;
    }

}
