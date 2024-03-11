package pangolin.shop;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pangolin.shop.domain.config.service.ConfigDomainService;
import pangolin.shop.domain.config.service.param.ConfigReq;
import pangolin.shop.domain.config.service.param.ConfigResp;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/11 0:18
 */
@SpringBootTest(classes = {Application.class})
public class ApplicationTest {

    @Autowired
    private ConfigDomainService configDomainService;

    @Test
    public void test_xx() {
        ConfigResp configResp = configDomainService.queryConfigList(new ConfigReq());
        System.out.println(configResp);
    }

}
