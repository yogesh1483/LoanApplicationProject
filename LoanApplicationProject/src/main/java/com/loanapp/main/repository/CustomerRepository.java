package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.Customer;
import com.loanapp.main.entity.EnquiryDetails;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Iterable<Customer> findAllByApplicationStatus(String applicationStatus);

	public Iterable<Customer> findAllByApplicationStatusOrApplicationStatus(String status1, String status2);

	public Customer findByCustomerId(int customerId);

	public EnquiryDetails findByMobileNumber(double customerMobileNumber);
}
