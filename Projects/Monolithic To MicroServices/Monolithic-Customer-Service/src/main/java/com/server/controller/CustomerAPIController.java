package com.server.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.data.CustomerRepositoryDao;
import com.server.model.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerAPIController {

	protected Logger logger = Logger.getLogger(CustomerAPIController.class.getName());

	@Autowired
	CustomerRepositoryDao dao;

	 

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Customer> getPersons() {
		logger.info("CustomerController Get All Persons with DAO as "+dao.getClass().getName());
		Iterable<Customer> personList = dao.findAll();
		return (List<Customer>) personList;

		/*
		 * List<Person> result = new ArrayList<Customer>();
		 * personList.forEach(result::add);
		 */

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Customer> addNewPerson(@RequestBody Customer personIn) {
		logger.info("Person Request Body = " + personIn);
		Customer personPbj = dao.save(personIn);

		logger.info("Person added with id = " + personPbj.getId());

		if (personPbj != null)
			return new ResponseEntity<Customer>(personPbj, HttpStatus.CREATED);
		else
			return new ResponseEntity<Customer>(personPbj, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Customer> updatePerson(@RequestBody Customer personIn) {

		logger.info("Customer Update");
		Optional<Customer> obj = dao.findById(personIn.getId());	
		 

		if (obj != null) {
			Customer person = obj.get();
			/*
			 * Customer updateObj = new Customer(personIn.getId(), personIn.getFirstName(),
			 * personIn.getLastName(), personIn.getHouseNumber(),personIn.getStreet(),
			 * personIn.getCity(), personIn.getAccountNumber(),personIn.getBranch());
			 */
			Customer result = dao.save(personIn);

			return new ResponseEntity<Customer>(result, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/read/{id}")
	@ResponseBody
	ResponseEntity<Customer> getPerson(@PathVariable Integer id) {

		logger.info("Customer Read by ID");
		 
		Optional<Customer> obj = dao.findById(id);

		if (obj != null) {
			return new ResponseEntity<Customer>(obj.get(), HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/remove/{id}", method = DELETE)
	@ResponseBody
	ResponseEntity removeUser(@PathVariable int id) {
		logger.info("Customer Remove by id");
		boolean flag = dao.existsById(id);
		if (flag) {
			dao.deleteById(id);
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

}
