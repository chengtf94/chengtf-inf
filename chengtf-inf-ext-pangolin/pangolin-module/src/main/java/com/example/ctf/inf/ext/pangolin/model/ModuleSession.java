package com.example.ctf.inf.ext.pangolin.model;

import java.util.List;

/**
 * 模块会话接口：也就是模块上下文接口，用于记录业务匹配中的上下文，例如匹配的模块编码、业务参数等
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public interface ModuleSession<P> {

    /**
     * 获取匹配的模块编码列表，例如业务码、产品码叠加列表
     *
     * @return 匹配的模块编码列表
     */
    List<String> getMatchedModuleCodes();

    /**
     * 设置匹配的模块编码列表
     *
     * @param matchedModuleCodes 匹配的模块编码列表
     */
    void setMatchedModuleCodes(List<String> matchedModuleCodes);

    /**
     * 获取会话上下文的参数
     *
     * @return 会话上下文的参数
     */
    default P getParam() {
        return (P) this;
    }

}
