package com.financialManagement.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financialManagement.customer.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Customer findCustomerDetails(Long CustomerId);
	
}
