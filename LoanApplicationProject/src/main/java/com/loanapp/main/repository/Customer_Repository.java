package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.Customer;

public interface Customer_Repository extends JpaRepository<Customer, Integer>{
	public Iterable<Customer> findAllByApplicationStatus(String applicationStatus);
}
