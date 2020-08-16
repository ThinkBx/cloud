package com.cloud.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流Bean，ip的限流
 * @author fjj
 * @date 2020/5/23 23:57
 */
@Configuration
public class BeanConfig {

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
//        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }
}