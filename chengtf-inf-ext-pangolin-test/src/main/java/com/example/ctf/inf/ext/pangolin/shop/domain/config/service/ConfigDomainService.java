package com.example.ctf.inf.ext.pangolin.shop.domain.config.service;

import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigModel;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param.ConfigCutOffReq;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param.ConfigFilterReq;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param.ConfigResp;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param.ConfigReq;

import java.util.List;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:12
 */
public interface ConfigDomainService {

    /**
     * 查询配置
     * @param configReq
     * @return
     */
    ConfigResp queryConfigList(ConfigReq configReq);


    /**
     * 查询配置
     * @param configReq
     * @return
     */
    List<ConfigModel> filterConfigList(ConfigFilterReq configReq, List<ConfigModel> configModels);

    /**
     * 查询配置
     * @param configReq
     * @return
     */
    List<ConfigModel> cutOffConfigList(ConfigCutOffReq configReq, List<ConfigModel> configModels);



}
