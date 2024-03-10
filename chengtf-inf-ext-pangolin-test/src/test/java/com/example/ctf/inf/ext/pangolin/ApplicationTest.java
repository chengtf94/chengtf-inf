package com.example.ctf.inf.ext.pangolin;

import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.ConfigDomainService;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param.ConfigReq;
import com.example.ctf.inf.ext.pangolin.shop.domain.config.service.param.ConfigResp;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/11 0:18
 */
@SpringBootTest
public class ApplicationTest {


    @Resource
    private ConfigDomainService configDomainService;

    @Test
    public void test_xx() {
        ConfigResp configResp = configDomainService.queryConfigList(new ConfigReq());
        System.out.println(configResp);
    }

}
