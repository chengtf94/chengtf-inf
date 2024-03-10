package com.example.ctf.inf.ext.pangolin.shop.domain.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 券配置信息
 * @author: chengtf
 * @date: 2024/3/10 22:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponConfigModel {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 实例ID
     */
    private Long instanceId;

    /**
     * 活动类型
     */
    private String activityType;

}
