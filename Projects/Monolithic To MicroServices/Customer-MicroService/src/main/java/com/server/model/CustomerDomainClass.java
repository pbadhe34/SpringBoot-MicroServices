package com.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 //Wrapper Class for Customer,Address and Account
public class CustomerDomainClass {
	
	private Customer customer;	
	 
	 
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private Address address;

	 
	private Account account;
	
	public CustomerDomainClass() {
		System.out.println("CustomerDomainClass default ");
	}

	public CustomerDomainClass(Address address, Account account,Customer custObj) {
		System.out.println("CustomerDomainClass account and address()");
		this.customer = custObj;
		this.address = address;
		this.account = account;
	}


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
		;
	}

	 

}
