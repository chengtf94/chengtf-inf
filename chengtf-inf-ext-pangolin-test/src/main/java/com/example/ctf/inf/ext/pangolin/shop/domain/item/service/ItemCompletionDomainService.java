package com.example.ctf.inf.ext.pangolin.shop.domain.item.service;

import com.example.ctf.inf.ext.pangolin.shop.domain.item.service.param.ItemCompletionReq;
import com.example.ctf.inf.ext.pangolin.shop.domain.item.service.param.ItemCompletionResp;

/**
 * @description: 商品补全领域服务
 * @author: chengtf
 * @date: 2024/3/10 23:30
 */
public interface ItemCompletionDomainService {


    /**
     * 商品补全
     * @param itemCompletionReq
     * @return
     */
    ItemCompletionResp itemCompletion(ItemCompletionReq itemCompletionReq);

}
