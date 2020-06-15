package com.server.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.model.Customer;

import java.util.List;

 
public interface CustomerRepositoryDao extends CrudRepository<Customer, Integer> {
	
	 

}
