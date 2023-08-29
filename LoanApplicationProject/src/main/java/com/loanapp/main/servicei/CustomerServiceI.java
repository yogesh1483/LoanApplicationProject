package com.loanapp.main.servicei;

import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.Customer;

public interface CustomerServiceI {

	Customer setCustomer(Customer customer, MultipartFile addressProof, MultipartFile panCard, MultipartFile addharCard,
			MultipartFile photo, MultipartFile signature, MultipartFile salarySlips);

}
