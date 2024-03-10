package com.example.ctf.inf.ext.pangolin.shop.domain.item.ability;

import com.example.ctf.inf.ext.pangolin.ability.Ability;
import com.example.ctf.inf.ext.pangolin.annotation.BaseAbility;
import com.example.ctf.inf.ext.pangolin.shop.domain.item.service.param.ItemCompletionReq;
import com.example.ctf.inf.ext.pangolin.shop.domain.item.service.param.ItemCompletionResp;

/**
 * @description: 商品补全领域服务
 * @author: chengtf
 * @date: 2024/3/10 23:30
 */
@BaseAbility
public interface ItemCompletionAbility extends Ability<Object> {

    String ABILITY_CODE = "com.example.ctf.inf.ext.pangolin.domain.item.ability.ItemCompletionAbility";

    /**
     * 商品补全
     * @param itemCompletionReq
     * @return
     */
    ItemCompletionResp itemCompletion(ItemCompletionReq itemCompletionReq);

}
