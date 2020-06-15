package com.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	protected Logger logger = Logger.getLogger(ApiController.class.getName());
	
	private List<Person> persons;
	
	public ApiController() {
		persons = new ArrayList<>();
		persons.add(new Person(1, "Munna", "MBBBS", 22));
		persons.add(new Person(2, "Kartik", "Waghmare", 33));
		persons.add(new Person(3, "Tommy", "Alter", 25));
		persons.add(new Person(4, "Maya", "Mohan", 54));
	}
	
	@RequestMapping("/person")
	public List<Person> findAll() {
		logger.info("Api.findAll()");
		return persons;
	}
	
	@RequestMapping("/person/{id}")
	public Person findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Api.findById(%d)", id));
		return persons.stream().filter(p -> (p.getId().intValue() == id)).findAny().get();
	}
	
}
