package com.loanapp.main.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.Admin;
import com.loanapp.main.exception.UserNotFoundException;

public interface LoanAppServiceI {

	Admin saveAdmin(Admin admin, MultipartFile profileimg);

	List<Admin> adminLogin(String ausername, String apassword) throws UserNotFoundException;

}
