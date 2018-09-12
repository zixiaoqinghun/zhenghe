package com.richard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: richard 
 * @date: 2018年9月6日 下午11:45:27
 * @version:
 * @Description: 
 * 		1. application.yml中配置eureka的服务器server的配置信息
 * 		2. 注解@EnableEurekaServer开启Eureka服务注册功能
 */
@EnableEurekaServer  //开启注册中心功能
@SpringBootApplication
public class App8761 {
	public static void main(String[] args) {
		SpringApplication.run(App8761.class, args);
	}
}
