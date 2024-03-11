package pangolin.shop.activity.ability.impl;

import pangolin.shop.activity.ability.ComponentRecallActivity;
import pangolin.shop.activity.model.ComponentBizModel;
import pangolin.shop.activity.model.ComponentContext;
import com.example.ctf.inf.ext.pangolin.annotation.AbilityCode;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:47
 */
@AbilityCode(ComponentRecallActivity.ABILITY_CODE)
public class CategoryComponentRecallActivityImpl implements ComponentRecallActivity  {
    @Override
    public ComponentBizModel recallComponent(ComponentContext context) {
        return null;
    }

}
