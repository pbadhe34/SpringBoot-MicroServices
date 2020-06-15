package org.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.model.Person;

 
public interface PersonDao extends CrudRepository<Person, Integer> {
	
	List<Person> findByFirstName(String firstName);
	Person findByLastName(String lastName);

}
