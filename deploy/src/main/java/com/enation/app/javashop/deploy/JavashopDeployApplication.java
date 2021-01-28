package com.enation.app.javashop.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


/**
 * javashop 安装器工程
 *
 * @author kingapex
 * @version v1.0.0
 * @since v7.0.0
 * 2018年1月22日 上午10:01:32
 * "com.enation.app.javashop.framework.database," +
 */
@SpringBootApplication
@ComponentScan(value = {
        "com.enation.app.javashop.framework.database.impl",
        "com.enation.app.javashop.framework.sncreator",
        "com.enation.app.javashop.framework.context",
        "com.enation.app.javashop.framework.exception",
        "com.enation.app.javashop.framework.redis.configure.builders",
        "com.enation.app.javashop.deploy"})
public class
JavashopDeployApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(JavashopDeployApplication.class, args);

    }
}
