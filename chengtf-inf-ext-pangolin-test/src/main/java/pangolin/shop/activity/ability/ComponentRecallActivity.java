package pangolin.shop.activity.ability;

import com.example.ctf.inf.ext.pangolin.ability.Ability;
import pangolin.shop.activity.model.ComponentBizModel;
import pangolin.shop.activity.model.ComponentContext;
import com.example.ctf.inf.ext.pangolin.annotation.BaseAbility;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:44
 */
@BaseAbility
public interface ComponentRecallActivity extends Ability<ComponentContext> {

    String ABILITY_CODE = "com.example.ctf.inf.ext.pangolin.activity.component.ability.ComponentRecallActivity";

    /**
     * 组件召回
     * @param context
     * @return
     */
    ComponentBizModel recallComponent(ComponentContext context);


}
