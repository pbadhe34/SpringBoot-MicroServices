package com.service.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/*
 * 
 * Without Zuul Gateway server
 * Employee service
 * http://localhost:8090/
 * http://lo/1calhost:8090/1
 * http://localhost:8090/department/1
 * 
 * http://localhost:8090/organization/1
 */

/*
 * With ZUUL Gateway server
 * http://localhost:8060/zuul-employee/
 * http://localhost:8060/zuul-employee/1
 * http://localhost:8060/zuul-employee/department/2
 * http://localhost:8060/zuul-employee/organization/2
 *  
 */
@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}
	
}
