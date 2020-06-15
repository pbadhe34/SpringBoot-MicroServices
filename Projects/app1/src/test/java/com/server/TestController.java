package com.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
 @RequestMapping("/test")
 public String testWeb() {
	 return "Welcome from Web";
 }
}
