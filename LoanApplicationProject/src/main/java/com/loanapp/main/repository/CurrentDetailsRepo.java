package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.CurrentLoanDetails;

public interface CurrentDetailsRepo extends JpaRepository<CurrentLoanDetails, Integer>{

}
