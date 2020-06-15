package com.server.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.model.Address;

import java.util.List;

 
public interface AddressRepositoryDao extends CrudRepository<Address, Integer> {
	
	 

}
