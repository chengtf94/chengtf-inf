package com.example.ctf.inf.ext.pangolin.model.model;

import java.util.List;

/**
 * 模块会话基础参数对象：可以被业务继承，构建业务对象
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public abstract class BaseParam<P> implements ModuleSession {

    /**
     * 匹配的模块编码列表
     */
    private List<String> matchedModuleCodes;

    @Override
    public List<String> getMatchedModuleCodes() {
        return matchedModuleCodes;
    }

    @Override
    public void setMatchedModuleCodes(List matchedModuleCodes) {
        this.matchedModuleCodes = matchedModuleCodes;
    }

}
