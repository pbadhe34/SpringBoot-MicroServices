package com.app2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroserviceTwoController {
	
	@Autowired
	Environment environment;
	
	@RequestMapping(value="/test2")
	public String getData(){
		String port = environment.getProperty("local.server.port");
		return "Microservice 2 running in :" + port; 
	}

}
