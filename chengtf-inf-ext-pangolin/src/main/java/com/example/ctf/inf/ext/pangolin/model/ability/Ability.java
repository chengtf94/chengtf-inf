package com.example.ctf.inf.ext.pangolin.model.ability;

import org.example.inf.ext.sprite.module.ModulePriority;

/**
 * 能力接口
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public interface Ability<P> extends ModulePriority {

    /**
     * 是否支持该能力执行
     *
     * @param param 业务对象
     * @return 是否支持该能力执行
     */
    default boolean isSupport(P param) {
        return true;
    }

    @Override
    default int getPriority() {
        return 0;
    }

}
