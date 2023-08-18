package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.AccountDetails;

public interface LoanAppRepository extends JpaRepository<AccountDetails, Integer>{

}
