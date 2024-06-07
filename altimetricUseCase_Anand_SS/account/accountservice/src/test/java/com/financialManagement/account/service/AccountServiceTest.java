package com.financialManagement.account.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.financialManagement.account.entity.AccountDetail;
import com.financialManagement.account.entity.Customer;
import com.financialManagement.account.repository.AccountDetailRepo;

public class AccountServiceTest {

	private static final String CUSTOMER_SERVICE_BASE_URL = "http://finance-management-service/api/customers/";

	@Autowired
	private AccountService accountService;

	@Test
	public void depositServiceTest() {
		AccountDetail accountDetail = accountService.createNewAccountForCustomer((long) 1);
		accountDetail.setAccountBalance(1000.00);
		AccountDetail updatedAccountDetail = accountService.depositService((long) 1, 100.00);
		assertTrue(updatedAccountDetail.getAccountBalance().equals(1100.00));
	}

	@Test
	public void withdrawServiceTest() {
		AccountDetail accountDetail = accountService.createNewAccountForCustomer((long) 1);
		accountDetail.setAccountBalance(1000.00);
		AccountDetail updatedAccountDetail = accountService.withdrawService((long) 1, 100.00);
		assertTrue(updatedAccountDetail.getAccountBalance().equals(900.00));
	}
	
	@Test
	public void deactivateAccountTest() throws Exception {
		AccountDetail accountDetail = accountService.createNewAccountForCustomer((long) 1);
		accountDetail.setStatus(true);
		
		AccountDetail updatedAccountDetail = accountService.deactivateAccount((long) 1);
		assertFalse(updatedAccountDetail.getStatus());
	}

	@Test
	public void activateAccountTest() throws Exception {
		AccountDetail accountDetail = accountService.createNewAccountForCustomer((long) 1);
		accountDetail.setStatus(false);
		
		AccountDetail updatedAccountDetail = accountService.deactivateAccount((long) 1);
		assertTrue(updatedAccountDetail.getStatus());
	}
	
	@Test
	public void createNewAccountForCustomerTest() {
		AccountDetail accountDetail = accountService.createNewAccountForCustomer((long) 1);
		
		assertTrue(accountDetail.getCustomerId().equals((long) 1));
	}

	@Test
	public void deleteAccountDetailsTest() throws Exception {
		AccountDetail accountDetail = accountService.createNewAccountForCustomer((long) 1);
		AccountDetail accountDetailToBeDeleted = accountService.deleteAccountDetails((long) 1);
		
		assertTrue(accountDetailToBeDeleted.getCustomerId().equals((long) 1));
	}
	
	
}
