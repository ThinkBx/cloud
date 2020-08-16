package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Gateway Application
 * @author fjj
 * @date 2020/5/23 10:05
 */
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		System.err.println("(✿◕‿◕✿)✦✦✦✦✦✦服务网关启动完成✦✦✦✦✦✦(✿◕‿◕✿)");
	}
}
