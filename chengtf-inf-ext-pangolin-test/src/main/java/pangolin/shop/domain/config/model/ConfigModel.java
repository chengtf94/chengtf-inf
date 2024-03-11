package pangolin.shop.domain.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description: 配置域模型
 * @author: chengtf
 * @date: 2024/3/10 22:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigModel {

    /**
     * ID
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 跳转链接
     */
    private String skipUrl;

    /**
     * 颜色
     */
    private String color;

    /**
     * 商品配置信息
     */
    private List<ItemConfigModel> itemConfigModels;

    /**
     * 券配置信息
     */
    private List<CouponConfigModel> couponConfigModels;

    /**
     * 扩展结果Map
     */
    private Map<String, Object> extMap;

}
