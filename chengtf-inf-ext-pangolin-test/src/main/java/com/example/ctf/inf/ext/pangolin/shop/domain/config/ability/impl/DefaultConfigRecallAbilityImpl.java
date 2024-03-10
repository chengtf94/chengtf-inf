package com.example.ctf.inf.ext.pangolin.shop.domain.config.ability.impl;

import com.example.ctf.inf.ext.pangolin.Module;
import com.example.ctf.inf.ext.pangolin.annotation.AbilityCode;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.ability.ConfigRecallAbility;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.extensionpoint.DefaultConfigRecallExtPt;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigContext;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigModel;
import com.google.common.collect.Lists;
import lombok.NonNull;

import java.util.List;

/**
 * @description: 默认配置域召回能力实例，配置域太大了，实际上还可以抽象几套固定的能力实例
 * @author: chengtf
 * @date: 2024/3/10 22:36
 */
@AbilityCode(ConfigRecallAbility.ABILITY_CODE)
public class DefaultConfigRecallAbilityImpl implements ConfigRecallAbility {

    @Override
    public boolean isSupport(ConfigContext context) {
        return Boolean.TRUE.equals(Module.executeFirstNotNull(DefaultConfigRecallExtPt.class, context, ext -> ext.isEnableAbility(context)));
    }

    @Override
    public List<ConfigModel> recallConfig(@NonNull ConfigContext context) {
        // 真实的能力肯定是封装的gateway数据源，不同的组件取数逻辑不同
        if ("张三".equals(context.getUserInfo())) {
            return Lists.newArrayList(ConfigModel.builder().title("张三的大促海报配置").build());
        }
        return null;
    }

}
