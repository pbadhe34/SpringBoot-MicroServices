package com.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fat_customer")
public class Customer {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)	 
	private Integer id;	 

	private String firstName;
	private String lastName;

	// Customer Address parameter values
	private int houseNumber;
	private String street;
	private String city;

	// Customer Account values
	private int accountNumber;
	private String branch;
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer() {
		System.out.println("Customer default ()");
	}
	
	public Customer(Integer id,String firstName,String lastName,int houseNumber,
			String street,String city,int accountNumber,String branch)
	{
		System.out.println("Customer Parameterized()");
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.houseNumber=houseNumber;
		this.street= street;
		this.city= city;
		this.accountNumber = accountNumber;
		this.branch = branch;
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
	

	public int getHouseNumber() {

		return houseNumber;
	}
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

	public void setHouseNumber(int houseNumber) {

		this.houseNumber = houseNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getBranch() {

		return branch;
	}

	public void setBranch(String branch) {

		this.branch = branch;
	}

}
