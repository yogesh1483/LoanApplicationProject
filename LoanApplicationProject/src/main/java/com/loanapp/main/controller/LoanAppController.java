package com.loanapp.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.main.entity.Admin;
import com.loanapp.main.entity.BaseResponse;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.exception.UserNotFoundException;
import com.loanapp.main.servicei.LoanAppServiceI;


@RestController
@RequestMapping("/loanapp")
public class LoanAppController {

	@Autowired
	LoanAppServiceI loanAppServiceI;
	
	@PostMapping("/addAdmin")
	public ResponseEntity<BaseResponse<Admin>> addAdmin(@RequestPart("admin") String adminJson,
			@RequestPart("profile") MultipartFile profileimg){
		ObjectMapper om = new ObjectMapper();
		try {
			Admin admin = om.readValue(adminJson, Admin.class);
			Admin adb = loanAppServiceI.saveAdmin(admin, profileimg);
			return new ResponseEntity<BaseResponse<Admin>>
			(new BaseResponse<Admin>(201, "Admin Added Successfully", new Date(), adb), HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/adminLogin/{ausername}/{apassword}")
	public ResponseEntity<BaseResponse<List<Admin>>> adminLogin(@PathVariable String ausername, @PathVariable String apassword) throws UserNotFoundException{
		List<Admin> ad = loanAppServiceI.adminLogin(ausername, apassword);
		return new ResponseEntity<BaseResponse<List<Admin>>>(
				new BaseResponse<List<Admin>>(200, "Admin Present", new Date(), ad), HttpStatus.OK);
	}
	
	
	@PostMapping("/addUser")
	public ResponseEntity<BaseResponse<Users>> addUser(@RequestPart("user") String userJson,
			@RequestPart("profile") MultipartFile profileImg) throws UserCanNotCreatedException
	{
		ObjectMapper om = new ObjectMapper();
		try {
			Users user = om.readValue(userJson, Users.class);
			Users udb=loanAppServiceI.saveUser(user,profileImg);
			return new ResponseEntity<BaseResponse<Users>>
			(new BaseResponse<Users>(201, "User Created Successfully", new Date(), udb), HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
