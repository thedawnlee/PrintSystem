package com.dawn.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DruidConfig {


    /**
     * spring.datasource 中的配置和 DruidDataSource 中的属性绑定
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource  druidDataSource () {
        return new DruidDataSource();
    }
}
