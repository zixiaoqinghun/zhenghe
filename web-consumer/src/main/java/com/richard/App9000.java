package com.richard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient //开启eureka服务发现功能
@EnableHystrix   //开启Hystrix   或者（@EnableCircuitBreaker）都可以使用hystrix的服务熔断功能
@EnableHystrixDashboard  //开启hystrix的仪表盘功能    http://localhost:9000/hystrix
@EnableCircuitBreaker
public class App9000 {
	
	@LoadBalanced //开启负载均衡机制
	@Bean("myRestTemplate")
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App9000.class, args);
	}
}
