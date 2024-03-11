package pangolin.shop.domain.config.service.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description: 配置请求参数
 * @author: chengtf
 * @date: 2024/3/10 23:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigCutOffReq {

    /**
     * 店铺信息
     */
    private Object shopInfo;

    /**
     * 用户信息
     */
    private String userInfo;

    /**
     * AB信息
     */
    private Object abInfo;

    /**
     * 通用请求参数
     */
    private Object commonQueryParam;

    /**
     * 自定义请求参数
     */
    private Object queryParam;

    /**
     * 扩展参数Map
     */
    private Map<String, Object> extMap;

    /**
     * 商品补全信息
     */
    private Map<Long, ItemInfo> itemInfoMap;

    /**
     * 券补全信息
     */
    private Map<Long, CouponInfo> couponInfoMap;

}
