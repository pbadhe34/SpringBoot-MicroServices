package com.server.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.model.Account;

import java.util.List;

 
public interface AccountRepositoryDao extends CrudRepository<Account, Integer> {
	
	 

}
