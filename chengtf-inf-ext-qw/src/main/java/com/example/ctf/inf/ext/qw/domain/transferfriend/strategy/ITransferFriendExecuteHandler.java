package com.example.ctf.inf.ext.qw.domain.transferfriend.strategy;

import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendRelationBO;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendRelationDelRecordBO;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendTransferExecuteContext;
import com.example.ctf.inf.ext.qw.domain.transferfriend.strategy.model.QwFriendTransferPlanBO;
import com.example.ctf.inf.ext.qw.plugin.IPlugin;

import java.util.List;

/**
 * @description: 好友转移计划执行器接口
 * @author: chengtf
 * @date: 2024/3/26 22:21
 */
public interface ITransferFriendExecuteHandler extends IPlugin {

    /**
     * 获取待处理的转移计划列表
     * @param tenantId 租户ID
     * @param transferType 转移类型
     * @return 待处理的转移计划列表
     */
    List<QwFriendTransferPlanBO> listWaitingPlan(Integer tenantId, Integer transferType);

    /**
     * 获取好友关系列表
     * @param context 上下文
     * @return 好友关系列表
     */
    List<QwFriendRelationBO> listFriendRelation(QwFriendTransferExecuteContext context);

    /**
     * 获取已删除的好友关系列表
     * @param context 上下文
     * @return 已删除的好友关系列表
     */
    List<QwFriendRelationDelRecordBO> listDelFriendRelation(QwFriendTransferExecuteContext context);

}
