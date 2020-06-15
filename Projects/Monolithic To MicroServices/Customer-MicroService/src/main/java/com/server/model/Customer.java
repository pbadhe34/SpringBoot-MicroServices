package com.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="micro_customer")
public class Customer {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;
	private String firstName;
	private String lastName;

	  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer(int id, String firstName, String lastName) {
		System.out.println("Customer. params Customer()");
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	 

	public Customer() {
		System.out.println("Customer default ");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	 

}
