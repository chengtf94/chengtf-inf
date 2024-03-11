package pangolin.shop.domain.config.ability.impl;

import com.example.ctf.inf.ext.pangolin.Module;
import com.example.ctf.inf.ext.pangolin.annotation.AbilityCode;
import pangolin.shop.domain.config.extensionpoint.ItemFeedsConfigRecallExtPt;
import pangolin.shop.domain.config.ability.ConfigRecallAbility;
import pangolin.shop.domain.config.model.ConfigContext;
import pangolin.shop.domain.config.model.ConfigModel;

import java.util.List;

/**
 * @description: 商品feeds流配置召回能力实例
 * @author: chengtf
 * @date: 2024/3/10 23:07
 */
@AbilityCode(ConfigRecallAbility.ABILITY_CODE)
public class ItemFeedsConfigRecallAbilityImpl implements ConfigRecallAbility {

    @Override
    public boolean isSupport(ConfigContext context) {
        return Boolean.TRUE.equals(Module.executeFirstNotNull(ItemFeedsConfigRecallExtPt.class, context, ext -> ext.isEnableAbility(context)));
    }

    @Override
    public List<ConfigModel> recallConfig(ConfigContext context) {
        return null;
    }

}
