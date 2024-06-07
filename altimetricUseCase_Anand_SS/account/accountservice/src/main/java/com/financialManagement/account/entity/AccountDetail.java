package com.financialManagement.account.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class AccountDetail {
	
	@Id
	private Long accountId;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	private Boolean status;
	
	private Double accountBalance;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public AccountDetail(Long accountId, Long customerId, Boolean status, Double accountBalance) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.status = status;
		this.accountBalance = accountBalance;
	}

	public AccountDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
