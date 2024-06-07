package com.financialManagement.customer.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financialManagement.customer.entity.Customer;
import com.financialManagement.customer.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	public Customer addNewCustomer(Customer customer) throws Exception {

		if (Objects.isNull(customer.getName())) {
			throw new Exception("Please provide valid name");
		}
		if (Objects.isNull(customer.getContactnumber())) {
			throw new Exception("Please provide valid contact Number");
		}
		if (Objects.isNull(customer.getRegisteredMailId())) {
			throw new Exception("Please provide valid mail id");
		}

		return customerRepo.save(customer);

	}

	public Customer getCustomerById(Long customerid) {

		return customerRepo.findCustomerDetails(customerid);
	}

	public List<Customer> getAllCustomer() {
		return customerRepo.findAll();
	}

	public Customer updateCustomerDetails(Long customerid, Customer updatedCustomerDetails) throws Exception {

		if (Objects.isNull(updatedCustomerDetails.getName())) {
			throw new Exception("Please provide valid name");
		}
		if (Objects.isNull(updatedCustomerDetails.getContactnumber())) {
			throw new Exception("Please provide valid contact Number");
		}
		if (Objects.isNull(updatedCustomerDetails.getRegisteredMailId())) {
			throw new Exception("Please provide valid mail id");
		}

		Customer customerToBeUpdated = customerRepo.findCustomerDetails(customerid);
		if (Objects.nonNull(customerToBeUpdated)) {
			customerToBeUpdated.setName(updatedCustomerDetails.getName());
			customerToBeUpdated.setContactnumber(updatedCustomerDetails.getContactnumber());
			customerToBeUpdated.setRegisteredMailId(updatedCustomerDetails.getRegisteredMailId());
			return customerToBeUpdated;
		}
		return null;
	}
	
	public Customer deleteCustomer(Long customerid) {
		
		Customer customerToBeDeleted = customerRepo.findCustomerDetails(customerid);
		if (Objects.nonNull(customerToBeDeleted)) {
			customerRepo.delete(customerToBeDeleted);
		}
		return customerToBeDeleted;
	}

}
