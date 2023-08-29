package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Iterable<Customer> findAllByApplicationStatus(String applicationStatus);

	public Iterable<Customer> findAllByApplicationStatusOrApplicationStatus(String status1, String status2);
}
