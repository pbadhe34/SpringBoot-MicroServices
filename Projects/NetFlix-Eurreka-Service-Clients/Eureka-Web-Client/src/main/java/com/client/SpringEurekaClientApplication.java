package com.client;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableEurekaClient : To register in  Euireaka discovery
@RestController
public class SpringEurekaClientApplication implements AppController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(SpringEurekaClientApplication.class, args);
    }

    @Override
    public String getData() {
    	Applications apps = eurekaClient.getApplications();
    	String currentApp = eurekaClient.getApplication(appName).getName();
    	System.out.println("Current App registed in Eureka as "+currentApp);
    	
    	List<Application> listApps = apps.getRegisteredApplications();
    	ListIterator<Application> litr = listApps.listIterator();
    	 
        
        while(litr.hasNext()){
           Application app = litr.next();
           System.out.println("App registered is "+app.getName());
          
        }
        return String.format("The apps registerd in Eureka  are  '%s'!",apps.size() );
    }
}
