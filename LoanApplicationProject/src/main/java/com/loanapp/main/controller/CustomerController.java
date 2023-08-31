package com.loanapp.main.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.main.entity.BaseResponse;
import com.loanapp.main.entity.Customer;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.servicei.CustomerServiceI;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	CustomerServiceI cs;

	
	
	//FOR APPLICATION FORM
	
	
	//FOR ADDING CUSTOMER DETAILS
	@PostMapping(value = "/addCustomer")
	public ResponseEntity<BaseResponse<Customer>> addCustomer(@RequestPart("customer") String customerJson,
			@RequestPart("addressProof") MultipartFile addressProof, @RequestPart("panCard") MultipartFile panCard,
			@RequestPart("addharCard") MultipartFile addharCard, @RequestPart("photo") MultipartFile photo,
			@RequestPart("signature") MultipartFile signature, @RequestPart("salarySlips") MultipartFile salarySlips) {
		ObjectMapper om = new ObjectMapper();
	try {
			Customer customer = om.readValue(customerJson, Customer.class);
			Customer customerdb = cs.setCustomer(customer, addressProof, panCard,
					addharCard, photo,signature, salarySlips);
			
			return new ResponseEntity<BaseResponse<Customer>>(
			new BaseResponse<Customer>(201, "Customer Created Successfully", new Date(), customerdb),
					HttpStatus.CREATED);
		} 
	catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	//FOR GETTING CUSTOMER DETAILS BY STATUS
	@GetMapping("/getCustomerByStatus/{status1}/{status2}")
	public Iterable<Customer> getCustomerByStatus(@PathVariable("status1") String status1,
			@PathVariable("status2") String status2) {
		return cs.getCustomerByStatus(status1, status2);
	}


	//FOR UPDATING CUSTOMER DETAILS BY ID
	@PutMapping("/updateCustomerBycustomerId/{customerId}")
	public ResponseEntity<BaseResponse<Customer>> updateCustomerBycustomerId(
			@PathVariable("customerId") int customerId,
			@RequestBody Customer c) {
		
		 cs.updateCustomerBycustomerId(customerId,c);
		 
			return new ResponseEntity<BaseResponse<Customer>>(
		new BaseResponse<Customer>(201, "Customer Updated Successfully", new Date(), c),
					HttpStatus.OK);
	}
	
}
