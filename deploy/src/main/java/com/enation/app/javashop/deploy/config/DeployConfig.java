package com.enation.app.javashop.deploy.config;

import com.enation.app.javashop.framework.JavashopConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: liuyulei
 * @create: 2019-12-25 18:12
 * @version:1.0
 * @since:7.1.4
 **/
@Configuration
public class DeployConfig {

    @Bean
    public JavashopConfig javashopConfig(){
        return  new JavashopConfig();
    }
}
