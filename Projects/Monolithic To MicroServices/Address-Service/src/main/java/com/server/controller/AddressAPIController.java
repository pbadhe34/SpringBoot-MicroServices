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

import com.server.data.AddressRepositoryDao;
import com.server.model.Address;
 
 

@RestController
@RequestMapping("/address")
public class AddressAPIController {

	protected Logger logger = Logger.getLogger(AddressAPIController.class.getName());

	@Autowired
	AddressRepositoryDao dao;
	 

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Address> getAddresses() {
		logger.info("AddressController Get All Persons with DAO as "+dao.getClass().getName());
		Iterable<Address> AddressList = dao.findAll();
		return (List<Address>) AddressList;

		 

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Address> addNewAddress(@RequestBody Address personIn) {
		logger.info("Account Request Body = " + personIn);
		Address personPbj = dao.save(personIn);

		logger.info("Address added with id = " + personPbj.getId());

		if (personPbj != null)
			return new ResponseEntity<Address>(personPbj, HttpStatus.CREATED);
		else
			return new ResponseEntity<Address>(personPbj, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Address> updateAddress(@RequestBody Address accobj) {

		logger.info("Address Update");
		Optional<Address> obj = dao.findById(accobj.getId());	
		 

		if (obj != null) {
			Address person = obj.get();
			 
			Address result = dao.save(accobj);

			return new ResponseEntity<Address>(result, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/read/{id}")
	@ResponseBody
	ResponseEntity<Address> getAddress(@PathVariable Integer id) {

		logger.info("Address Read by ID");
		 
		Optional<Address> obj = dao.findById(id);

		if (obj != null) {
			return new ResponseEntity<Address>(obj.get(), HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/remove/{id}", method = DELETE)
	@ResponseBody
	ResponseEntity removeAddress(@PathVariable int id) {
		logger.info("Address Remove by id");
		boolean flag = dao.existsById(id);
		if (flag) {
			dao.deleteById(id);
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

}
