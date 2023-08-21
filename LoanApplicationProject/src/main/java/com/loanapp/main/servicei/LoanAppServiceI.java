package com.loanapp.main.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.exception.UserNotFoundException;

public interface LoanAppServiceI {

	Users saveUser(Users user, MultipartFile profileImg)throws UserCanNotCreatedException;

	EnquiryDetails addEnquiryDetails(EnquiryDetails enquiryDetails);

	Users getusers(String userName, String userPassword);

}
