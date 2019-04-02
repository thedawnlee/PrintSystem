package com.wrq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wangqian on 2019/3/30.
 */
@Component
@ConfigurationProperties(prefix = "config")
public class ParameterConfig {

    private String imageHost;

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
