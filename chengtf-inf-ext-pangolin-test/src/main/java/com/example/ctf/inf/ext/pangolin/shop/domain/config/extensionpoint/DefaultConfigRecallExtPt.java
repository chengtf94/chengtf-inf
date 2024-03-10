package com.example.ctf.inf.ext.pangolin.shop.domain.config.extensionpoint;

import com.example.ctf.inf.ext.pangolin.annotation.BaseExtensionPoint;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigContext;
import com.example.ctf.inf.ext.pangolin.extensionpoint.ExtensionPoint;

/**
 * @description: 默认配置召回扩展点
 * @author: chengtf
 * @date: 2024/3/10 22:56
 */
@BaseExtensionPoint
public interface DefaultConfigRecallExtPt extends ExtensionPoint {

    /**
     * 是否启用能力实例
     */
    default Boolean isEnableAbility(ConfigContext context){
        return null;
    }


}
