package com.example.ctf.inf.ext.qw.plugin;

import com.google.common.base.Preconditions;
import lombok.Data;

import java.util.Objects;

/**
 * @description: 扩展点身份标识
 * @author: chengtf
 * @date: 2024/3/25 21:47
 */
@Data
public class PluginIdentifier {

    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 业务线ID
     */
    private Integer bizTypeId;

    public String buildKey() {
        check();
        return tenantId + "_" + bizTypeId;
    }

    public void check() {
        Preconditions.checkArgument(Objects.nonNull(tenantId));
        Preconditions.checkArgument(Objects.nonNull(bizTypeId));
    }

}
