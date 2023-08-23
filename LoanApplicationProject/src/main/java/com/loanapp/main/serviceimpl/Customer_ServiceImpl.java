package com.loanapp.main.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanapp.main.entity.Customer;
import com.loanapp.main.repository.Customer_Repository;
import com.loanapp.main.servicei.Customer_Service;

@Service
public class Customer_ServiceImpl implements Customer_Service {

	@Autowired
	Customer_Repository cr;
	
	@Override
	public Customer setCustomer(Customer c) {
		return cr.save(c);
	}

	@Override
	public Iterable<Customer> getCustomerbyStatus(String applicationStatus) {
	   return cr.findAllByApplicationStatus(applicationStatus);
	}

}
