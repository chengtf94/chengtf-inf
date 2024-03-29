package pangolin.shop.domain.config.service.impl;

import com.example.ctf.inf.ext.pangolin.Module;
import pangolin.shop.domain.config.ability.ConfigRecallAbility;
import pangolin.shop.domain.config.model.ConfigContext;
import pangolin.shop.domain.config.model.ConfigModel;
import pangolin.shop.domain.config.service.mapper.ConfigContextMapper;
import pangolin.shop.domain.config.service.param.ConfigCutOffReq;
import pangolin.shop.domain.config.service.param.ConfigFilterReq;
import pangolin.shop.domain.config.service.param.ConfigResp;
import pangolin.shop.domain.config.service.ConfigDomainService;
import pangolin.shop.domain.config.service.param.ConfigReq;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:15
 */
@Service
public class ConfigDomainServiceImpl implements ConfigDomainService {

    @Override
    public ConfigResp queryConfigList(@NonNull ConfigReq configReq) {
        ConfigContext configContext = ConfigContextMapper.INSTANCE.toConfigContext(configReq);
        ConfigRecallAbility configRecallAbility = Module.getFirstAbility(ConfigRecallAbility.class, configContext);
        if (Objects.isNull(configRecallAbility)) {
            return null;
        }
        return ConfigResp.builder().configModels(configRecallAbility.recallConfig(configContext)).build();
    }

    @Override
    public List<ConfigModel> filterConfigList(ConfigFilterReq configReq, List<ConfigModel> configModels) {
        return null;
    }

    @Override
    public List<ConfigModel> cutOffConfigList(ConfigCutOffReq configReq, List<ConfigModel> configModels) {
        return null;
    }


}
