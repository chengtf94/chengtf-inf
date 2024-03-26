package com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.zhusu;

import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.ITransferFriendExecuteHandler;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendRelationBO;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendTransferPlanBO;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.constants.QwPluginConstants;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendRelationDelRecordBO;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendTransferExecuteContext;
import com.example.ctf.inf.ext.qw.plugin.PluginAnnotation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 好友转移计划执行器实现（住宿租户）
 * @author: chengtf
 * @date: 2024/3/26 23:19
 */
@Service
@PluginAnnotation(tenantId = QwPluginConstants.TENANT_ZHUSU)
public class ZhusuTransferFriendExecuteHandler implements ITransferFriendExecuteHandler {
    @Override
    public List<QwFriendTransferPlanBO> listWaitingPlan(Integer tenantId, Integer transferType) {
        System.out.println("zhusu listWaitingPlan");
        return null;
    }

    @Override
    public List<QwFriendRelationBO> listFriendRelation(QwFriendTransferExecuteContext context) {
        System.out.println("zhusu listFriendRelation");
        return null;
    }

    @Override
    public List<QwFriendRelationDelRecordBO> listDelFriendRelation(QwFriendTransferExecuteContext context) {
        System.out.println("zhusu listDelFriendRelation");
        return null;
    }
}
