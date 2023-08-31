package com.loanapp.main.servicei;

import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.Customer;
import com.loanapp.main.entity.CustomerAddress;

public interface CustomerServiceI {

	Iterable<Customer> getCustomerByStatus(String status1, String status2);

	Customer updateCustomerBycustomerId(int customerId, Customer c);

	Customer setCustomer(Customer customer, MultipartFile addressProof, MultipartFile panCard, MultipartFile addharCard,
			MultipartFile photo, MultipartFile signature, MultipartFile salarySlips);

}
