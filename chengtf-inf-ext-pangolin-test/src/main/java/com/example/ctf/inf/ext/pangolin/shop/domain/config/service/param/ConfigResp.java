package com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param;

import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigResp {

    /**
     * 配置列表
     */
    private List<ConfigModel> configModels;

}
