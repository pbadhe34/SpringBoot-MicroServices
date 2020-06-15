package com.server.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.net.URL;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.data.CustomerRepositoryDao;
import com.server.model.Account;
import com.server.model.Address;
import com.server.model.Customer;
import com.server.model.CustomerDomainClass;
 
 

@RestController
@RequestMapping("/micro-customers")
public class CustomerAPIController {

	protected Logger logger = Logger.getLogger(CustomerAPIController.class.getName());

	@Autowired
	CustomerRepositoryDao dao;
	 

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CustomerDomainClass> getCustomers() {
		logger.info("CustomerController Get All Customers with DAO as "+dao.getClass().getName());
		Iterable<Customer> CustomerList = dao.findAll();
		
		//The customer list with emebeded address and account objects
		List<CustomerDomainClass> resultList = new ArrayList<CustomerDomainClass>(); 
		
		CustomerDomainClass dataObj= null;
		 for (Customer temp : CustomerList) {
			 logger.info("Customer id details are "+temp.getId());
			    Address adrObj = fetchCustomerAddress(temp.getId());
			    Account acObj = fetchCustomerAccount(temp.getId());
			    
			    dataObj = new CustomerDomainClass();
			    dataObj.setCustomer(temp);
			    dataObj.setAddress(adrObj);
			    dataObj.setAccount(acObj);
			    resultList.add(dataObj);            
	        }
		 logger.info("CustomerController Get All Customers are "+resultList.size());
		 return resultList;		 

	}
	
	private Address fetchCustomerAddress(int customerID) {
		// convert string in json format to Java object with Jackson lib
				ObjectMapper mapper = new ObjectMapper();
				Address obj = null;

				Address currentAddress = null;
				URL addressUrl;
				try {
					addressUrl = new URL("http://localhost:2090/address/read/"+customerID);
					// JSON from URL to Object
					currentAddress = mapper.readValue(addressUrl, com.server.model.Address.class);

			/*
			 * Rertrive list of addreesses if needed
			 * addressUrl = new URL("http://localhost:2090/address/all");
			 * Address[] objects = mapper.readValue(addressUrl, Address[].class);
			 * System.out.println("No of addresses are " + objects.length);
			 * 
			 * for (Address current : objects) { System.out.println(current.getId()); int
			 * addressId = current.getId(); if (customerId == addressId) { currentAddress =
			 * current; break; }
			 * 
			 * }
			 */
					System.out.println("The read address is " + currentAddress.getCity());

				} catch (Exception e) {
					System.out.println("The error in address retrieveal is " + e.getMessage());
					currentAddress= null;
				}

				return currentAddress;

			}
			

	 	
    private Account fetchCustomerAccount(int customerID) {
    	ObjectMapper mapper = new ObjectMapper();		 

		Account currentAccount = null;
		URL accountUrl;
		try {
			accountUrl = new URL("http://localhost:1040/accounts/read/"+customerID);
			// JSON from URL to Object
			currentAccount = mapper.readValue(accountUrl, com.server.model.Account.class);

			 
			System.out.println("The current account is " + currentAccount.getAcNumber());

		} catch (Exception e) {
			System.out.println("Error in current account is " + e.getMessage());
			currentAccount = null;
		}

		return currentAccount;


	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer personIn) {
		logger.info("Customer Request Body = " + personIn);
		Customer personPbj = dao.save(personIn);

		logger.info("New Customer added with id = " + personPbj.getId());

		if (personPbj != null)
			return new ResponseEntity<Customer>(personPbj, HttpStatus.CREATED);
		else
			return new ResponseEntity<Customer>(personPbj, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Customer> updateAddress(@RequestBody Customer accobj) {

		logger.info("Customer Update");
		Optional<Customer> obj = dao.findById(accobj.getId());	
		 

		if (obj != null) {
			Customer person = obj.get();
			 
			Customer result = dao.save(accobj);

			return new ResponseEntity<Customer>(result, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/read/{id}")
	@ResponseBody
	ResponseEntity<Customer> getAddress(@PathVariable Integer id) {

		logger.info("Customer Read by ID");
		 
		Optional<Customer> obj = dao.findById(id);

		if (obj != null) {
			return new ResponseEntity<Customer>(obj.get(), HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/remove/{id}", method = DELETE)
	@ResponseBody
	ResponseEntity removeAddress(@PathVariable int id) {
		logger.info("Customer Remove by id");
		boolean flag = dao.existsById(id);
		if (flag) {
			dao.deleteById(id);
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

}
