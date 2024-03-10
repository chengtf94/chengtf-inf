package com.example.ctf.inf.ext.pangolin.extensionpoint;

import com.example.ctf.inf.ext.pangolin.model.ModuleSession;

import java.util.List;

/**
 * 扩展点调度器接口
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
public interface ExtensionPointInvoker {

    /**
     * 获取所有的扩展点实现
     *
     * @param extClass 扩展点类型
     * @param session  业务对象
     * @param <Ext>    扩展点类型定义
     * @param <T>      业务对象类型定义
     * @return 所有的扩展点实现
     */
    <Ext extends ExtensionPoint, T extends ModuleSession> List<Ext> getAllExtension(Class<Ext> extClass, T session);

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
    <Ext extends ExtensionPoint, T extends ModuleSession, R> R executeFirstNotNull(Class<Ext> extClass, T session, ExtensionPointCallback<Ext, R> callback);

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
    <Ext extends ExtensionPoint, T extends ModuleSession, R> List<R> executeAll(Class<Ext> extClass, T session, ExtensionPointCallback<Ext, R> callback);

}
