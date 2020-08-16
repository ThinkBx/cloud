package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Config Application
 * @author fjj
 * @date 2020/5/22 15:05
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
		System.err.println("(✿◕‿◕✿)✦✦✦✦✦✦配置中心启动完成✦✦✦✦✦✦(✿◕‿◕✿)");
	}

}
