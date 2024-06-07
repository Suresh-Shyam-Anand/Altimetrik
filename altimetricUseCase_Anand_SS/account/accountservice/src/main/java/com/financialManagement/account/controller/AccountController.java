package com.financialManagement.account.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.financialManagement.account.entity.AccountDetail;
import com.financialManagement.account.entity.Customer;
import com.financialManagement.account.entity.CustomerAccountDetails;
import com.financialManagement.account.service.AccountService;

@RestController
@RequestMapping("/api/accountdetails")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RestTemplate restTemplate;

	private static final String CUSTOMER_SERVICE_BASE_URL = "http://finance-management-service/api/customers/";

	@PostMapping("/depositMoney/{customerId}")
	public ResponseEntity<Object> depositMoney(@PathVariable Long customerId, @RequestParam Double amountToDeposit) {
		if (amountToDeposit > 0) {
			AccountDetail accountDetail = accountService.depositService(customerId, amountToDeposit);

			if (Objects.nonNull(accountDetail)) {
				return new ResponseEntity<>("Invalid Account", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(accountDetail, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Deposit Amount invalid", HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/withdrawMoney/{customerId}")
	public ResponseEntity<Object> withdrawMoney(@PathVariable Long customerId,
			@RequestParam Double amountToBeWithdrawn) {
		if (amountToBeWithdrawn > 0) {
			AccountDetail accountDetail = accountService.withdrawService(customerId, amountToBeWithdrawn);

			if (Objects.nonNull(accountDetail)) {
				return new ResponseEntity<>("Invalid Account", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(accountDetail, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Withdrawal Amount invalid", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Object> getCustomerAccountDetails(@PathVariable Long customerId) {
		Customer customer = restTemplate.getForObject(CUSTOMER_SERVICE_BASE_URL + "getCustomerById/" + customerId,
				Customer.class);
		AccountDetail accountDetail = accountService.getAccountDetails(customerId);

		if (Objects.isNull(customer)) {
			return new ResponseEntity<>("Invalid Customer", HttpStatus.NOT_FOUND);
		}
		if (Objects.isNull(accountDetail)) {
			return new ResponseEntity<>("Invalid Account", HttpStatus.NOT_FOUND);
		}

		CustomerAccountDetails customerAccountDetails = new CustomerAccountDetails();
		customerAccountDetails.setCustomer(customer);
		customerAccountDetails.setAccountDetail(accountDetail);

		return new ResponseEntity<>(accountDetail, HttpStatus.OK);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Object> deleteAccountDetails(@PathVariable Long customerId) throws Exception {
		Customer customer = restTemplate.getForObject(CUSTOMER_SERVICE_BASE_URL + "getCustomerById/" + customerId,
				Customer.class);
		if (Objects.isNull(customer)) {
			return new ResponseEntity<>("Invalid Customer", HttpStatus.NOT_FOUND);
		}

		AccountDetail accountDetail = accountService.deleteAccountDetails(customerId);
		if (Objects.isNull(accountDetail)) {
			return new ResponseEntity<>("Invalid Account", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(accountDetail, HttpStatus.OK);
		}
	}
}
