package com.example.ctf.inf.ext.pangolin.shop.domain.config.ability;

import com.example.ctf.inf.ext.pangolin.ability.Ability;
import com.example.ctf.inf.ext.pangolin.annotation.BaseAbility;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigContext;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigModel;

import java.util.List;

/**
 * @description: 配置領域过滤能力
 * @author: chengtf
 * @date: 2024/3/10 22:28
 */
@BaseAbility
public interface ConfigFilterAbility extends Ability<ConfigContext> {

    String ABILITY_CODE = "com.example.ctf.inf.ext.pangolin.domain.config.ability.ConfigFilterAbility";

    /**
     * 配置过滤
     * @param configContext
     * @param configModels
     * @return
     */
    List<ConfigModel> filterConfig(ConfigContext configContext, List<ConfigModel> configModels);

}
