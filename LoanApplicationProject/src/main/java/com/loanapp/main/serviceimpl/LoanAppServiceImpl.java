package com.loanapp.main.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.Admin;
import com.loanapp.main.exception.UserNotFoundException;
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

	@Override
	public List<Admin> adminLogin(String ausername, String apassword) throws UserNotFoundException {
		List<Admin> ad = ar.findAllByAusernameAndApassword(ausername,apassword);
		if(ad.size()!=0){
			return ad;
		}
		else{
			throw new UserNotFoundException("No User Found in Database:- "+ausername);
		}
	}

}
