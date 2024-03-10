package com.example.ctf.inf.ext.pangolin.model.ext.spring;

import com.example.ctf.inf.ext.pangolin.model.Module;
import com.example.ctf.inf.ext.pangolin.model.ext.ExtensionPoint;
import com.example.ctf.inf.ext.pangolin.model.ext.ExtensionPointCallback;
import com.example.ctf.inf.ext.pangolin.model.ext.ExtensionPointInvoker;
import com.example.ctf.inf.ext.pangolin.model.model.ModuleSession;
import com.example.ctf.inf.ext.pangolin.model.module.ModuleManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 扩展点调度器实现：基于Spring
 *
 * @author: chengtf
 * @date: 2023/10/28
 */
@Component
public class ExtensionPointInvokerSpringImpl implements ExtensionPointInvoker {

    @Resource
    private ModuleManager moduleManager;

    @Override
    public <Ext extends ExtensionPoint, T extends ModuleSession> List<Ext> getAllExtension(Class<Ext> extClass, T session) {
        if (null == extClass || null == session) {
            return new ArrayList<>(0);
        }
        // 匹配一下模块编码，否则返回为空，除非上层已经匹配了matchedModuleCodes
        checkAndMatchModule(session);
        return moduleManager.getAllExtension(extClass, session.getMatchedModuleCodes());
    }

    @Override
    public <Ext extends ExtensionPoint, T extends ModuleSession, R> R executeFirstNotNull(Class<Ext> extClass, T session, ExtensionPointCallback<Ext, R> callback) {
        // 匹配一下模块编码，否则返回为空，除非上层已经匹配了matchedModuleCodes
        checkAndMatchModule(session);
        List<Ext> allExtension = getAllExtension(extClass, session);
        for (Ext ext : allExtension) {
            R r = callback.callback(ext);
            if (null != r) {
                return r;
            }
        }
        return null;
    }

    @Override
    public <Ext extends ExtensionPoint, T extends ModuleSession, R> List<R> executeAll(Class<Ext> extClass, T session, ExtensionPointCallback<Ext, R> callback) {
        // 匹配一下模块编码，否则返回为空，除非上层已经匹配了matchedModuleCodes
        checkAndMatchModule(session);
        List<Ext> allExtension = getAllExtension(extClass, session);
        List<R> results = new ArrayList<>(allExtension.size());
        for (Ext ext : allExtension) {
            R r = callback.callback(ext);
            if (null != r) {
                results.add(r);
            }
        }
        return results;
    }

    /**
     * 检查并初始化模块编码
     *
     * @param target 模块上下文参数
     * @param <T>    模块上下文参数类型
     */
    private static <T extends ModuleSession> void checkAndMatchModule(T target) {
        if (null == target) {
            return;
        }
        if (null == target.getMatchedModuleCodes() && target instanceof ModuleSession) {
            Module.initModuleCode(target);
        }
    }


}
