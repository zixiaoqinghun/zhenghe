package com.richard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TicketController {

	@GetMapping("/index")
	public String index(){
		return "SUCCESS";
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/buy/{name}")
	public String buyTicket(@PathVariable String name){
		System.out.println("..................");
		String ticket = restTemplate.getForObject("http://WEB-PROVIDER7002/ticket", String.class);
		return name + " 购买了:"+ ticket;
	}
}
