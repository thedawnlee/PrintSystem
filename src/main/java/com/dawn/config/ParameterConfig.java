package com.dawn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "config")
@Data
public class ParameterConfig {

    private String imageHost;

    private String alipayCallbackUrl;

    private String shopNotFound;

}
