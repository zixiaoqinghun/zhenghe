package com.richard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.richard.utils.JsonResult;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	
	String prefix_url="http://WEB-PROVIDER";
	
	@HystrixCommand(fallbackMethod="getUserFallBack")
	public Object getUser(String userId){
		String url = prefix_url +"/user/get/";
		Object object = this.restTemplate.getForObject(url+userId, Object.class);
		return object;
	}
	
	public Object getUserFallBack(String userId) {
		JsonResult jr = new JsonResult();
		jr.setStatus("1001");
		jr.setResult("连接超时或者系统内部异常，请联系管理员！");
        return jr;
    }
}
