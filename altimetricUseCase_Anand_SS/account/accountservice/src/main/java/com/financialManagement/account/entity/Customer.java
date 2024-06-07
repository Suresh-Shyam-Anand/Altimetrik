package com.financialManagement.account.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Customer {

	@Id
	private Long id;
	
	private String name;
	
	private Long contactnumber;
	
	private String registeredMailId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(Long contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getRegisteredMailId() {
		return registeredMailId;
	}

	public void setRegisteredMailId(String registeredMailId) {
		this.registeredMailId = registeredMailId;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String name, Long contactnumber, String registeredMailId) {
		super();
		this.id = id;
		this.name = name;
		this.contactnumber = contactnumber;
		this.registeredMailId = registeredMailId;
	}
	
}
