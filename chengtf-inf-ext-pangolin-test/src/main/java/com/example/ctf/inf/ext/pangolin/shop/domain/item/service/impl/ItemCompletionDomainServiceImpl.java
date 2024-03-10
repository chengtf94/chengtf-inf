package com.example.ctf.inf.ext.pangolin.shop.domain.item.service.impl;

import com.example.ctf.inf.ext.pangolin.Module;
import com.example.ctf.inf.ext.pangolin.shop.domain.item.ability.ItemCompletionAbility;
import com.example.ctf.inf.ext.pangolin.shop.domain.item.service.ItemCompletionDomainService;
import com.example.ctf.inf.ext.pangolin.shop.domain.item.service.param.ItemCompletionReq;
import com.example.ctf.inf.ext.pangolin.shop.domain.item.service.param.ItemCompletionResp;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:33
 */
@Service
public class ItemCompletionDomainServiceImpl implements ItemCompletionDomainService {

    @Override
    public ItemCompletionResp itemCompletion(ItemCompletionReq itemCompletionReq) {
        return Module.getFirstAbility(ItemCompletionAbility.class, itemCompletionReq)
                .itemCompletion(itemCompletionReq);
    }

}
