package com.loanapp.main.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.main.entity.AllPersonalDocuments;
import com.loanapp.main.entity.CibilStatus;
import com.loanapp.main.entity.Customer;
import com.loanapp.main.servicei.Customer_Service;

@RestController
@RequestMapping("/AddCust")
public class Customer_Controller {

	@Autowired
	Customer_Service cs;

	@PostMapping(value = "/AddCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestPart("cust") String cust, @RequestPart("pan") MultipartFile pan,
			@RequestPart("photo") MultipartFile photo, @RequestPart("adhar") MultipartFile adhar,
			@RequestPart("salaryslip") MultipartFile salaryslip, @RequestPart("signature") MultipartFile signature,
			@RequestPart("addproof") MultipartFile addproof) throws ClassCastException, IOException {

		AllPersonalDocuments d = new AllPersonalDocuments();
		d.setPanCard(pan.getBytes());
		d.setPhoto(photo.getBytes());
		d.setAddharCard(adhar.getBytes());
		d.setSalarySlips(salaryslip.getBytes());
		d.setSignature(signature.getBytes());
		d.setAddressProof(addproof.getBytes());

		ObjectMapper om = new ObjectMapper();
		Customer c = om.readValue(cust, Customer.class);

		c.setApplicationStatus(String.valueOf(CibilStatus.pending));
		c.setAllPersonalDoc(d);

		return new ResponseEntity<Customer>(cs.setCustomer(c), HttpStatus.OK);
	}

}
