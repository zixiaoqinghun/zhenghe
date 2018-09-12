package com.richard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2 //开启swagger2的API在线文档
@SpringBootApplication
@MapperScan(basePackages = "com.richard.mapper")
@EnableCaching //开启缓存注解
@EnableEurekaClient   //开启服务注册功能
public class App7002 {
	public static void main(String[] args) {
		SpringApplication.run(App7002.class, args);
	}
}
