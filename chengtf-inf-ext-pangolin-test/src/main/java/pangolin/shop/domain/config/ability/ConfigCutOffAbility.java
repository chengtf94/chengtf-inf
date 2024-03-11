package pangolin.shop.domain.config.ability;

import com.example.ctf.inf.ext.pangolin.ability.Ability;
import com.example.ctf.inf.ext.pangolin.annotation.BaseAbility;
import pangolin.shop.domain.config.model.ConfigContext;
import pangolin.shop.domain.config.model.ConfigModel;

import java.util.List;

/**
 * @description: 配置領域截断能力
 * @author: chengtf
 * @date: 2024/3/10 22:28
 */
@BaseAbility
public interface ConfigCutOffAbility extends Ability<ConfigContext> {

    String ABILITY_CODE = "com.example.ctf.inf.ext.pangolin.domain.config.ability.ConfigCutOffAbility";

    /**
     * 配置截断
     * @param configContext
     * @param configModels
     * @return
     */
    List<ConfigModel> cutOffConfig(ConfigContext configContext, List<ConfigModel> configModels);

}
