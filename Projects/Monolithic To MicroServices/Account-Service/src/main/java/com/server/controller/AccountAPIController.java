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

import com.server.data.AccountRepositoryDao;
import com.server.model.Account;
 

@RestController
@RequestMapping("/accounts")
public class AccountAPIController {

	protected Logger logger = Logger.getLogger(AccountAPIController.class.getName());

	@Autowired
	AccountRepositoryDao dao;

	 

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Account> getAccountss() {
		logger.info("AccountController Get All Persons with DAO as "+dao.getClass().getName());
		Iterable<Account> AccountList = dao.findAll();
		return (List<Account>) AccountList;

		 

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Account> addNewAccount(@RequestBody Account personIn) {
		logger.info("Account Request Body = " + personIn);
		Account personPbj = dao.save(personIn);

		logger.info("Account added with id = " + personPbj.getId());

		if (personPbj != null)
			return new ResponseEntity<Account>(personPbj, HttpStatus.CREATED);
		else
			return new ResponseEntity<Account>(personPbj, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Account> updatePerson(@RequestBody Account accobj) {

		logger.info("Account Update");
		Optional<Account> obj = dao.findById(accobj.getId());	
		 

		if (obj != null) {
			Account person = obj.get();
			 
			Account result = dao.save(accobj);

			return new ResponseEntity<Account>(result, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/read/{id}")
	@ResponseBody
	ResponseEntity<Account> getAccount(@PathVariable Integer id) {

		logger.info("Account Read by ID");
		 
		Optional<Account> obj = dao.findById(id);

		if (obj != null) {
			return new ResponseEntity<Account>(obj.get(), HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/remove/{id}", method = DELETE)
	@ResponseBody
	ResponseEntity removeUser(@PathVariable int id) {
		logger.info("Account Remove by id");
		boolean flag = dao.existsById(id);
		if (flag) {
			dao.deleteById(id);
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

}
