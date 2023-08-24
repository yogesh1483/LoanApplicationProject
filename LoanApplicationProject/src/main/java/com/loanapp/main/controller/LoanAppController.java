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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.main.entity.BaseResponse;
import com.loanapp.main.entity.Cibil;
import com.loanapp.main.entity.ContactUs;
import com.loanapp.main.entity.CurrentLoanDetails;
import com.loanapp.main.entity.CustomerAddress;
import com.loanapp.main.entity.CustomerVerification;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.servicei.LoanAppServiceI;

@RestController
@RequestMapping("/loanapp")
@CrossOrigin("*")
public class LoanAppController {

	@Autowired
	LoanAppServiceI loanAppServiceI;
	
	@Autowired
	RestTemplate rs;

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
	
	
	@PostMapping("/addContactInfo")
	public ResponseEntity<BaseResponse<ContactUs>> addContactInfo(@RequestBody ContactUs contactUs){
	  ContactUs add = loanAppServiceI.addEnquiryDetails(contactUs);
		return new ResponseEntity<BaseResponse<ContactUs>>(
				new BaseResponse<ContactUs>(201, "Contact save Successfully", new Date(), contactUs),
				HttpStatus.CREATED);
     }
	
	
	@GetMapping("/getEnquiry")
	public ResponseEntity<BaseResponse<List<EnquiryDetails>>> viewEnquiry(){   
		List<EnquiryDetails> list = loanAppServiceI.getEnquiry();
		return new ResponseEntity<BaseResponse<List<EnquiryDetails>>>(
				new BaseResponse<List<EnquiryDetails>>(201, "Enquiry Received Successfully", new Date(), list), HttpStatus.CREATED);	
	}
	
	
	@GetMapping("/getEnquiryByStatus/{status1}/{status2}")
	public Iterable<EnquiryDetails> getEnquiryOnStatus(@PathVariable ("status1") String status1,@PathVariable ("status2") String status2){
		return loanAppServiceI.getEnquiryOnStatus(status1,status2);	
	}
	

	@PutMapping("/updateEnquiryStatus/{eId}")
	public ResponseEntity<BaseResponse<EnquiryDetails>> updateEnquiryStatus(@RequestBody EnquiryDetails enquiryDetails,
			                                                                @PathVariable ("eId") int eId)
	{       
		EnquiryDetails enquiryDetail   =  loanAppServiceI.updateEnquiryStatus(eId,enquiryDetails);
		               
		return new ResponseEntity<BaseResponse<EnquiryDetails>>(
				new BaseResponse<EnquiryDetails>(200, "Enquiry Received Successfully", new Date(),  enquiryDetail ),
				HttpStatus.OK);
		
	}


	
	//for adding current loan details
	@PostMapping("/addCurrentLoanDetails")
	public ResponseEntity<BaseResponse<CurrentLoanDetails>> addCurrentLoanDetails(@RequestBody CurrentLoanDetails currentLoanDetails){
		CurrentLoanDetails add = loanAppServiceI.addCurrentLoanDetails(currentLoanDetails);
		return new ResponseEntity<BaseResponse<CurrentLoanDetails>>(
				new BaseResponse<CurrentLoanDetails>(201, "Contact save Successfully", new Date(), currentLoanDetails),
				HttpStatus.CREATED);
     }
	
	//for adding Customer Address details
	@PostMapping("/addCustomerAddress")
	public ResponseEntity<BaseResponse<CustomerAddress>> addCurrentLoanDetails(@RequestBody CustomerAddress customerAddress){
		CustomerAddress add = loanAppServiceI.addCustomerAddress(customerAddress);
		return new ResponseEntity<BaseResponse<CustomerAddress>>(
				new BaseResponse<CustomerAddress>(201, "Contact save Successfully", new Date(), customerAddress),
				HttpStatus.CREATED);
     }
	         
	 
	//for adding Customer Verification details
	@PostMapping("/addCustomerVerification")
	public ResponseEntity<BaseResponse<CustomerVerification>> addCustomerVerification(@RequestBody CustomerVerification customerVerification){
		CustomerVerification add = loanAppServiceI.addCustomerVerification(customerVerification);
		return new ResponseEntity<BaseResponse<CustomerVerification>>(
				new BaseResponse<CustomerVerification>(201, "Contact save Successfully", new Date(), customerVerification),
				HttpStatus.CREATED);
     }

	@GetMapping("/checkCibil/{pancardNumber}")
	public Integer checkCibil(@PathVariable("pancardNumber") String pancardNumber){
		String url="http://localhost:8081/getCibilScore/"+pancardNumber;
		Integer cibilScore= rs.getForObject(url, Integer.class);
		System.out.println(cibilScore);
		return cibilScore;
		
     }
}
 