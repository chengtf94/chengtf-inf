package com.example.ctf.inf.ext.pangolin.extensionpoint.spring;

import com.example.ctf.inf.ext.pangolin.Module;
import com.example.ctf.inf.ext.pangolin.model.ModuleSession;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPoint;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPointCallback;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPointInvoker;
import com.example.ctf.inf.ext.pangolin.module.ModuleManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(extClass) || Objects.isNull(session)) {
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
        if (Objects.isNull(target)) {
            return;
        }
        if (Objects.isNull(target.getMatchedModuleCodes())) {
            // 若匹配的模块编码为null，说明尚未匹配过，则初始化
            Module.initModuleCode(target);
        }
    }


}
