package com.example.ctf.inf.ext.pangolin.shop.domain.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 商品配置信息
 * @author: chengtf
 * @date: 2024/3/10 22:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemConfigModel {

    /**
     * 商品ID
     */
    private Long itemId;

}
