package com.example.ctf.inf.ext.pangolin.model.ability;

import java.util.List;

/**
 * 能力点调度器接口
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public interface AbilityInvoker {

    /**
     * 获取所有的能力点实现
     *
     * @param abilityClass 能力点类型
     * @param param        业务对象
     * @param <A>          能力点类型定义
     * @param <P>          业务对象类型定义
     * @return 所有的能力点实现
     */
    <A extends Ability, P> List<A> getAllAbility(Class<A> abilityClass, P param);
}
