package com.cloud.gateway.config;

import com.cloud.gateway.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 鉴权配置
 * @author fjj
 * @date 2020/5/24 14:11
 */
@Configuration
public class GatewayFilterConfig {

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}