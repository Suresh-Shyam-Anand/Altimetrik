package com.financialManagement.account.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.financialManagement.account.entity.AccountDetail;
import com.financialManagement.account.entity.Customer;
import com.financialManagement.account.repository.AccountDetailRepo;

@Service
public class AccountService {

	private static final String CUSTOMER_SERVICE_BASE_URL = "http://finance-management-service/api/customers/";

	@Autowired
	private AccountDetailRepo accountDetailRepo;

	@Autowired
	private RestTemplate restTemplate;

	public AccountDetail depositService(Long customerId, Double amountToDeposit) {

		Customer customer = restTemplate.getForObject(CUSTOMER_SERVICE_BASE_URL + "getCustomerById/" + customerId,
				Customer.class);

		if (Objects.nonNull(customer)) {
			AccountDetail accountDetail = getOrCreateAccountDetails(customerId);
			accountDetail.setAccountBalance(accountDetail.getAccountBalance() + amountToDeposit);
			return accountDetailRepo.save(accountDetail);
		}

		return null;
	}

	public AccountDetail withdrawService(Long customerId, Double amountToBeWithdrawn) {
		Customer customer = restTemplate.getForObject(CUSTOMER_SERVICE_BASE_URL + "getCustomerById/" + customerId,
				Customer.class);

		if (Objects.nonNull(customer)) {
			AccountDetail accountDetail = getOrCreateAccountDetails(customerId);
			accountDetail.setAccountBalance(accountDetail.getAccountBalance() - amountToBeWithdrawn);
			return accountDetailRepo.save(accountDetail);
		}

		return null;
	}

	public AccountDetail getAccountDetails(Long customerId) {

		return accountDetailRepo.findByCustomerID(customerId);
	}

	public AccountDetail deactivateAccount(Long customerId) throws Exception {
		AccountDetail accountDetail = getAccountDetails(customerId);
		if (Objects.isNull(accountDetail)) {
			throw new Exception("Please provide valid customer id");
		} else {
			accountDetail.setStatus(false);
		}
		return accountDetailRepo.save(accountDetail);
	}

	public AccountDetail activateAccount(Long customerId) throws Exception {
		AccountDetail accountDetail = getAccountDetails(customerId);
		if (Objects.isNull(accountDetail)) {
			throw new Exception("Please provide valid customer id");
		} else {
			accountDetail.setStatus(true);
		}
		return accountDetailRepo.save(accountDetail);
	}

	public AccountDetail createNewAccountForCustomer(Long customerId) {
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setCustomerId(customerId);
		accountDetail.setAccountBalance(0.0);
		accountDetail.setStatus(true);

		return accountDetailRepo.save(accountDetail);
	}

	private AccountDetail getOrCreateAccountDetails(Long customerId) {

		AccountDetail accountDetail = getAccountDetails(customerId);
		if (Objects.isNull(accountDetail)) {
			accountDetail = createNewAccountForCustomer(customerId);
		}

		return accountDetail;
	}
	
	public AccountDetail deleteAccountDetails(Long customerId) throws Exception {
		AccountDetail accountDetail = getAccountDetails(customerId);
		if (Objects.isNull(accountDetail)) {
			throw new Exception("Please provide valid customer id");
		}
		accountDetailRepo.delete(accountDetail);
		
		return accountDetail;
	}
	
	
}
