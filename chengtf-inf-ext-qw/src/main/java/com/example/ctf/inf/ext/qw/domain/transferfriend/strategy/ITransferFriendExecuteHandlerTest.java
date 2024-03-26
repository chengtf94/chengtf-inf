package com.example.ctf.inf.ext.qw.domain.transferfriend.strategy;

import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.constants.QwPluginConstants;
import com.example.ctf.inf.ext.qw.plugin.IPluginLoader;
import com.example.ctf.inf.ext.qw.plugin.PluginIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/26 23:43
 */
@Configuration
public class ITransferFriendExecuteHandlerTest {

    @Autowired
    private IPluginLoader pluginLoader;

    @Bean
    public String testITransferFriendExecuteHandler () {
        PluginIdentifier identifier = PluginIdentifier.builder().tenantId(QwPluginConstants.TENANT_ZHUSU).bizTypeId(QwPluginConstants.DEFAULT_IZ_TYPE_ID).build();
        ITransferFriendExecuteHandler executeHandler = Optional.ofNullable(pluginLoader.load(ITransferFriendExecuteHandler.class, identifier)).orElse(null);
        System.out.println(executeHandler);
        return "testITransferFriendExecuteHandler";
    }

}
