package com.loanapp.main.servicei;

import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.Admin;

public interface LoanAppServiceI {

	Admin saveAdmin(Admin admin, MultipartFile profileimg);

}
