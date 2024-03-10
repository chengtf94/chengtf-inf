package com.example.ctf.inf.ext.pangolin.shop.domain.config.ability;

import com.example.ctf.inf.ext.pangolin.ability.Ability;
import com.example.ctf.inf.ext.pangolin.annotation.BaseAbility;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigContext;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigModel;

import java.util.List;

/**
 * @description: 配置領域召回能力：配置是C端导购店铺域内装修数据的抽象
 *                  在展示形式上，会有海报类、券带品等，例如大促海报、日常海报、券带品专区、活动专区、爆款推荐等
 *                  在数据源上，会有来自商家自装修、平台运营选投、平台智能推荐等
 * @author: chengtf
 * @date: 2024/3/10 22:28
 */
@BaseAbility
public interface ConfigRecallAbility extends Ability<ConfigContext> {

    String ABILITY_CODE = "com.example.ctf.inf.ext.pangolin.domain.config.ability.ConfigRecallAbility";

    /**
     * 配置召回
     * @param configContext
     * @return
     */
    List<ConfigModel> recallConfig(ConfigContext configContext);

}
