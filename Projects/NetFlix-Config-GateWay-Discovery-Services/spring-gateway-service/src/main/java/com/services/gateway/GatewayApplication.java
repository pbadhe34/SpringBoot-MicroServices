package com.services.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//Test  URLS 
//    With Spring Cloud  Gateway Department service
//   http://localhost:8060/department
//  http://localhost:8060/department/organization/2
//  http://localhost:8060/department/organization/2/with-employees


//With Spring Cloud  Gateway Employee service
//  http://localhost:8060/employee/
//  http://localhost:8060/employee/1
 //  http://localhost:8060/employee/2

//  http://localhost:8060/employee/department/2		 																																																																																										
//  http://localhost:8060/employee/organization/2


/*
 * //With Spring Cloud  Gateway Organization service
 * 
 *  http://localhost:8060/organization
 *  http://localhost:8060/organization/2
 *  
 *  http://localhost:8060/organization/2/with-departments
 *  http://localhost:8060/organization/2/with-employees
 *  http://localhost:8060/organization/2/with-departments-and-employees
 * 
 */
//Without Gateway
/* Department-Service
 * http://localhost:8091/ 
 * http://localhost:8091/2
 * http://localhost:8091/organization/1
 * http://localhost:8091/organization/2/with-employees
 * 
 * Employee Service
 * http://localhost:8090/ 
 *  http://localhost:8090/2    
 *   http://localhost:8090/department/1
 *   http://localhost:8090/organization/2
 *     
 *     
 *     Organization service
 *     //http://localhost:8092/
 *     http://localhost:8092/1
 *     http://localhost:8092/2
 *     //http://localhost:8092/2/with-departments
 *     http://localhost:8092/2/with-employees
 *     http://localhost:8092/2/with-departments-and-employees
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
}
