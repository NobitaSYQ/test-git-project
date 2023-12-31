package com.enation.app.javashop.deploy.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kingapex on 2018-12-28.
 *
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018-12-28
 */
@Configuration
@ConfigurationProperties(prefix="javashop")
public class Certificate {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
