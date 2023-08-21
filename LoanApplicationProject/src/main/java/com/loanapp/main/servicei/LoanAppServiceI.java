package com.loanapp.main.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.Admin;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.exception.UserNotFoundException;

public interface LoanAppServiceI {

	Admin saveAdmin(Admin admin, MultipartFile profileimg);

	List<Admin> adminLogin(String ausername, String apassword) throws UserNotFoundException;

	Users saveUser(Users user, MultipartFile profileImg)throws UserCanNotCreatedException;

	EnquiryDetails addEnquiryDetails(EnquiryDetails enquiryDetails);

	Users getusers(String userName, String userPassword);

}
