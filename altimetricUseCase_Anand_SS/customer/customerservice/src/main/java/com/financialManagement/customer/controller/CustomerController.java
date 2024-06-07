package com.financialManagement.customer.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financialManagement.customer.entity.Customer;
import com.financialManagement.customer.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Object> getCustomer(@PathVariable Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		if(Objects.isNull(customer)) {
			return new ResponseEntity<>("Invalid Customer", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomerDetails) throws Exception {
		Customer customer = customerService.updateCustomerDetails(customerId, updatedCustomerDetails);
		if(Objects.isNull(customer)) {
			return new ResponseEntity<>("Invalid Customer", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId) {
		Customer customerToBeDeleted = customerService.deleteCustomer(customerId);
		if(Objects.isNull(customerToBeDeleted)) {
			return new ResponseEntity<>("Invalid Customer", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customerToBeDeleted, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> createCustomerDetails(@RequestBody Customer customerToBeCreated) throws Exception {
		Customer customer = customerService.addNewCustomer(customerToBeCreated);
		if(Objects.isNull(customerToBeCreated)) {
			return new ResponseEntity<>("Invalid Customer", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customerToBeCreated, HttpStatus.OK);
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomer();
		
	}
	
}
