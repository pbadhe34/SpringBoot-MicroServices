package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"org.model", "org.controller","org.data"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.data"})
@EntityScan("org.model")
public class PersonDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonDataApplication.class, args);
	}

}
