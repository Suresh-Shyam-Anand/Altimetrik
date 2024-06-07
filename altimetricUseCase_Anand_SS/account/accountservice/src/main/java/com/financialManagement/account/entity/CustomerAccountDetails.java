package com.financialManagement.account.entity;

import lombok.Data;

@Data
public class CustomerAccountDetails {

	public CustomerAccountDetails(Customer customer, AccountDetail accountDetail) {
		super();
		this.customer = customer;
		this.accountDetail = accountDetail;
	}
	public CustomerAccountDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public AccountDetail getAccountDetail() {
		return accountDetail;
	}
	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}
	private Customer customer;
	private AccountDetail accountDetail;
	
}
