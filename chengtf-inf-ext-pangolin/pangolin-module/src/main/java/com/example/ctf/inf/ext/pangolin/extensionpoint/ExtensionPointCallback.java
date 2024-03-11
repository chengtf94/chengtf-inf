package com.example.ctf.inf.ext.pangolin.extensionpoint;

/**
 * 扩展点回调接口
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
//@FunctionalInterface
public interface ExtensionPointCallback<Ext, R> {

    /**
     * 回调方法
     *
     * @param ext 扩展点实现
     * @return 回调执行结果
     */
    R callback(Ext ext);

}
