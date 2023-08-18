package com.loanapp.main.controller;

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
import com.loanapp.main.entity.Admin;
import com.loanapp.main.servicei.LoanAppServiceI;


@RestController
@RequestMapping("/loanapp")
public class LoanAppController {
	
	@Autowired
	LoanAppServiceI loanAppServiceI;
	
	@PostMapping("/addAdmin")
	public ResponseEntity<Admin> addAdmin(@RequestPart("admin") String adminJson,
			@RequestPart("profile") MultipartFile profileimg){
		ObjectMapper om = new ObjectMapper();
		try {
			Admin admin = om.readValue(adminJson, Admin.class);
			Admin adb = loanAppServiceI.saveAdmin(admin, profileimg);
			return new ResponseEntity<Admin>(adb, HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
