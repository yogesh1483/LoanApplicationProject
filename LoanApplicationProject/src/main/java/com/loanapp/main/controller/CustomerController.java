package com.loanapp.main.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.main.entity.BaseResponse;
import com.loanapp.main.entity.Customer;
import com.loanapp.main.entity.Users;
import com.loanapp.main.servicei.CustomerServiceI;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerServiceI cs;

	@PostMapping(value = "/addCustomer")
	public ResponseEntity<BaseResponse<Customer>> addCustomer(@RequestPart("customer") String customerJson,
			@RequestPart("addressProof") MultipartFile addressProof, @RequestPart("panCard") MultipartFile panCard,
			@RequestPart("addharCard") MultipartFile addharCard, @RequestPart("photo") MultipartFile photo,
			@RequestPart("signature") MultipartFile signature, @RequestPart("salarySlips") MultipartFile salarySlips) {
		ObjectMapper om = new ObjectMapper();
		try {
			Customer customer = om.readValue(customerJson, Customer.class);
			Customer customerdb = cs.setCustomer(customer, addressProof, panCard, addharCard, photo,
					signature, salarySlips);
			return new ResponseEntity<BaseResponse<Customer>>(
					new BaseResponse<Customer>(201, "Customer Created Successfully", new Date(), customerdb),
					HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
