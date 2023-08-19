package com.loanapp.main.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.Admin;
import com.loanapp.main.repository.AdminRepo;
import com.loanapp.main.servicei.LoanAppServiceI;

@Service
public class LoanAppServiceImpl implements LoanAppServiceI {

	@Autowired
	AdminRepo ar;
	
	@Override
	public Admin saveAdmin(Admin admin, MultipartFile profileimg) {
		try {
			admin.setProfileimg(profileimg.getBytes());
			return ar.save(admin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
