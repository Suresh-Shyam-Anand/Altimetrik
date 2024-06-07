package com.financialManagement.customer.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.financialManagement.customer.entity.Customer;
import com.financialManagement.customer.repository.CustomerRepo;

public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	@Test
	public void addNewCustomerTest() throws Exception {

		Customer customerToBeAdded = new Customer((long) 1,"Dave",(long) 123456,"Dave@test.com");
		Customer customer = customerService.addNewCustomer(customerToBeAdded);
		assertTrue(customer.getId().equals(1));
		assertTrue(customer.getName().equals("Dave"));
		assertTrue(customer.getContactnumber().equals(123456));
		assertTrue(customer.getRegisteredMailId().equals("Dave@test.com"));
	}

	@Test
	public void getCustomerById() throws Exception {

		Customer customer = customerService.addNewCustomer(new Customer((long) 1,"Dave",(long) 123456,"Dave@test.com"));
		Customer customerRetrieved = customerService.getCustomerById((long) 1);
		assertTrue(customerRetrieved.getId().equals(1));
		assertTrue(customerRetrieved.getName().equals("Dave"));
		assertTrue(customerRetrieved.getContactnumber().equals(123456));
		assertTrue(customerRetrieved.getRegisteredMailId().equals("Dave@test.com"));
	}

	@Test
	public void updateCustomerDetails() throws Exception {
		
		Customer customer = customerService.addNewCustomer(new Customer((long) 1,"Dave",(long) 123456,"Dave@test.com"));
		Customer updatedCustomer = new Customer((long) 1,"Raven",(long) 1234567,"Raven@test.com");
		Customer customerToBeUpdated = customerService.updateCustomerDetails((long) 1, updatedCustomer);
		assertTrue(customerToBeUpdated.getId().equals(1));
		assertTrue(customerToBeUpdated.getName().equals("Raven"));
		assertTrue(customerToBeUpdated.getContactnumber().equals(123456));
		assertTrue(customerToBeUpdated.getRegisteredMailId().equals("Raven@test.com"));
	}
	
	@Test
	public void deleteCustomer() throws Exception {
		
		Customer customer = customerService.addNewCustomer(new Customer((long) 1,"Dave",(long) 123456,"Dave@test.com"));
		Customer customerToBeDeleted = customerService.deleteCustomer((long) 1);
		assertTrue(customerToBeDeleted.getId().equals(1));
		assertTrue(customerToBeDeleted.getName().equals("Raven"));
		assertTrue(customerToBeDeleted.getContactnumber().equals(123456));
		assertTrue(customerToBeDeleted.getRegisteredMailId().equals("Raven@test.com"));
	}

}
