package com.example.ctf.inf.ext.pangolin;

import com.example.ctf.inf.ext.pangolin.model.ModuleSession;

/**
 * 模块匹配器接口
 *
 * @author: chengtf
 * @date: 2023/10/29
 */
public interface ModuleMatcher<Context extends ModuleSession> extends ModulePriority {

    /**
     * 解析模块规则判断是否匹配
     *
     * @param context 模块参数
     * @return 是否匹配
     */
    boolean isMatch(Context context);

    /**
     * 获取模块编码
     *
     * @return 模块编码
     */
    String getModuleCode();

}
