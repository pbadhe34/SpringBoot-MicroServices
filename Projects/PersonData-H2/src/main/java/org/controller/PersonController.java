package org.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.model.Person;
import org.data.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

	protected Logger logger = Logger.getLogger(PersonController.class.getName());

	@Autowired
	PersonDao dao;

	/*
	 * @RequestMapping("/person") public List<Person> findAll() {
	 * logger.info("Api.findAll()"); return persons; }
	 * 
	 * @RequestMapping("/person/{id}") public Person findById(@PathVariable("id")
	 * Integer id) { logger.info(String.format("Api.findById(%d)", id)); return
	 * persons.stream().filter(p -> (p.getId().intValue() == id)).findAny().get(); }
	 */

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Person> getPersons() {
		logger.info("PersonController GetAll");
		Iterable<Person> personList = dao.findAll();
		return (List<Person>) personList;

		/*
		 * List<Person> result = new ArrayList<Person>();
		 * personList.forEach(result::add);
		 */

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Person> addNewPerson(@RequestBody Person personIn) {
		logger.info("Person Request Body = " + personIn);
		Person personPbj = dao.save(personIn);

		logger.info("Person added with id = " + personPbj.getId());

		if (personPbj != null)
			return new ResponseEntity<Person>(personPbj, HttpStatus.CREATED);
		else
			return new ResponseEntity<Person>(personPbj, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Person> updatePerson(@RequestBody Person personIn) {

		logger.info("Person Update");
		Optional<Person> obj = dao.findById(personIn.getId());

		if (obj != null) {
			Person person = obj.get();
			Person updateObj = new Person(personIn.getId(), personIn.getFirstName(), personIn.getLastName(),
					personIn.getAge());

			Person result = dao.save(personIn);

			return new ResponseEntity<Person>(result, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/read/{id}")
	@ResponseBody
	ResponseEntity<Person> getPerson(@PathVariable Integer id) {

		logger.info("Person Read by ID");
		 
		Optional<Person> obj = dao.findById(id);

		if (obj != null) {
			return new ResponseEntity<Person>(obj.get(), HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/remove/{id}", method = DELETE)
	@ResponseBody
	ResponseEntity removeUser(@PathVariable int id) {
		logger.info("Person Remove by id");
		boolean flag = dao.existsById(id);
		if (flag) {
			dao.deleteById(id);
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

}
