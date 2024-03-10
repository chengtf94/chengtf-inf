package com.example.ctf.inf.ext.pangolin.model.ability.spring;

import com.example.ctf.inf.ext.pangolin.model.ability.Ability;
import com.example.ctf.inf.ext.pangolin.model.ability.AbilityInvoker;
import com.example.ctf.inf.ext.pangolin.model.module.ModuleManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 能力点调度器实现：基于Spring
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Component
public class AbilityInvokerSpringImpl implements AbilityInvoker {

    @Resource
    private ModuleManager moduleManager;

    @Override
    public <A extends Ability, P> List<A> getAllAbility(Class<A> abilityClass, P param) {
        if (null == abilityClass || null == param) {
            return new ArrayList<>(0);
        }

        // 获取所有的能力点实现
        List<A> allAbility = moduleManager.getAllAbility(abilityClass);
        if (CollectionUtils.isEmpty(allAbility)) {
            return new ArrayList<>(0);
        }

        // 过滤业务对象支持的能力点实现
        allAbility = allAbility.stream().filter(o -> o.isSupport(param)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(allAbility)) {
            return new ArrayList<>(0);
        }

        // 按照优先级降序排序
        if (allAbility.size() > 1) {
            allAbility.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
        }

        return allAbility;
    }
}
