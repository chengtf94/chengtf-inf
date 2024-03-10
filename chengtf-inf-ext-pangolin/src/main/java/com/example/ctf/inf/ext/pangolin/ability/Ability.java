package com.example.ctf.inf.ext.pangolin.ability;

import com.example.ctf.inf.ext.pangolin.ModulePriority;

/**
 * 能力接口：继承ModulePriority接口，意味着能力具有优先级的概念，可以按优先级顺序选择个别或全部执行
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
        // 默认优先级为0
        return 0;
    }

}
