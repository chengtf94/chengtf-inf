package com.example.ctf.inf.ext.pangolin.shop.domain.config.service.mapper;

import com.example.ctf.inf.ext.pangolin.shop.domain.config.model.ConfigContext;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param.ConfigReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:19
 */
@Mapper
public interface ConfigContextMapper {

    ConfigContextMapper INSTANCE = Mappers.getMapper(ConfigContextMapper.class);

    ConfigContext toConfigContext(ConfigReq configReq);

}
