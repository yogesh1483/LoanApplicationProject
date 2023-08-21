package com.loanapp.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.main.entity.BaseResponse;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.exception.UserNotFoundException;
import com.loanapp.main.servicei.LoanAppServiceI;

@RestController
@RequestMapping("/loanapp")
@CrossOrigin("*")
public class LoanAppController {

	@Autowired
	LoanAppServiceI loanAppServiceI;

	@PostMapping("/addUser")
	public ResponseEntity<BaseResponse<Users>> addUser(@RequestPart("user") String userJson,
			@RequestPart("profile") MultipartFile profileImg) throws UserCanNotCreatedException {
		ObjectMapper om = new ObjectMapper();
		try {
			Users user = om.readValue(userJson, Users.class);
			Users udb = loanAppServiceI.saveUser(user, profileImg);
			return new ResponseEntity<BaseResponse<Users>>(
					new BaseResponse<Users>(201, "User Created Successfully", new Date(), udb), HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;

	}

	@PostMapping("/addEnquiryDetails")
	public ResponseEntity<BaseResponse<EnquiryDetails>> addEnquiryDetails(@RequestBody EnquiryDetails enquiryDetails) {
		EnquiryDetails enquiry = loanAppServiceI.addEnquiryDetails(enquiryDetails);
		return new ResponseEntity<BaseResponse<EnquiryDetails>>(
				new BaseResponse<EnquiryDetails>(201, "Enquiry Received Successfully", new Date(), enquiry),
				HttpStatus.CREATED);

	}

	@GetMapping("/getusers/{userName}/{userPassword}")
	public ResponseEntity<BaseResponse<Users>> getusers(@PathVariable String userName,
			@PathVariable String userPassword) {

		Users u = loanAppServiceI.getusers(userName, userPassword);

		return new ResponseEntity<BaseResponse<Users>>(
				new BaseResponse<Users>(201, "Enquiry Received Successfully", new Date(), u), HttpStatus.CREATED);

	}

}
