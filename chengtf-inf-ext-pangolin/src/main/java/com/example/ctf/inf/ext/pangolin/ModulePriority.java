package com.example.ctf.inf.ext.pangolin;

/**
 * 模块优先级接口：按照优先级从高到低排序，优先级高的模块实现优先执行
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public interface ModulePriority {

    /**
     * 获取模块优先级
     *
     * @return 模块优先级
     */
    int getPriority();

}
