package com.app1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroserviceOneController {
	
	@Autowired
	Environment environment;
	
	@RequestMapping(value="/test1")
	public String getResponse(){
		String port = environment.getProperty("local.server.port");
		return "Microservice 1 running in :" + port; 
	}

}
