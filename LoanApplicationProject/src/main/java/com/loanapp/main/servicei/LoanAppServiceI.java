package com.loanapp.main.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.ContactUs;
import com.loanapp.main.entity.CurrentLoanDetails;
import com.loanapp.main.entity.CustomerAddress;
import com.loanapp.main.entity.CustomerVerification;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.exception.UserNotFoundException;

public interface LoanAppServiceI {

	Users saveUser(Users user, MultipartFile profileImg)throws UserCanNotCreatedException;

	EnquiryDetails addEnquiryDetails(EnquiryDetails enquiryDetails);

	Users getusers(String userName, String userPassword);

	ContactUs addEnquiryDetails(ContactUs contactUs);

    List<EnquiryDetails> getEnquiry();

    CurrentLoanDetails addCurrentLoanDetails(CurrentLoanDetails currentLoanDetails);

	CustomerAddress addCustomerAddress(CustomerAddress customerAddress);

	CustomerVerification addCustomerVerification(CustomerVerification customerVerification);

}
