package pangolin.shop.domain.config.model;

import com.example.ctf.inf.ext.pangolin.model.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description: 配置域上下文對象
 * @author: chengtf
 * @date: 2024/3/10 22:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigContext extends BaseParam {

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

}
