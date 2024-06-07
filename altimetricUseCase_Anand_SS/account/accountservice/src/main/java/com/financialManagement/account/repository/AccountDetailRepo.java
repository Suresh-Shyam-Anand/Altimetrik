package com.financialManagement.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financialManagement.account.entity.AccountDetail;

@Repository
public interface AccountDetailRepo extends JpaRepository<AccountDetail, Long> {

	AccountDetail findByCustomerID(Long customerId);
	AccountDetail findAccountDetail(Long accountId);
	AccountDetail findByStatus(Boolean Status);
}
