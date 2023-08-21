package com.loanapp.main.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.entity.ContactUs;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.exception.UserNotFoundException;
import com.loanapp.main.repository.ContactUsRepo;
import com.loanapp.main.repository.EnquiryDetailsRepo;
import com.loanapp.main.repository.UsersRepo;
import com.loanapp.main.servicei.LoanAppServiceI;

@Service
public class LoanAppServiceImpl implements LoanAppServiceI {

	@Autowired
	Random rm;
	
	@Autowired
	UsersRepo ur;
	@Autowired
	EnquiryDetailsRepo er;
	@Autowired
	ContactUsRepo cr;
	@Autowired JavaMailSender sender;
	
	@Value("$spring.mail.username")
	private String formMail;

	@Override
	public Users saveUser(Users user, MultipartFile profileImg) throws UserCanNotCreatedException {
		if(user.getUserType()!=null) {
		try {
			user.setProfileImg(profileImg.getBytes());
			user.setUserName(user.getName()+""+user.getUserType()+"@"+rm.nextInt(4444));
			user.setUserPassword(user.getUserType()+"@"+rm.nextInt(1111));
			
			SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
			simpleMailMessage.setFrom(formMail);
			simpleMailMessage.setTo(user.getUserEmail());
			simpleMailMessage.setSubject("Loan Application Username And Password");
			simpleMailMessage.setText("Hello"+user.getName()+"\t Your Username: "+user.getUserName()+"/t And Password: "+user.getUserPassword());
			sender.send(simpleMailMessage);
			

			
			return ur.save(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else
		{
			throw new UserCanNotCreatedException("User Can Not Be Created Provide Valid Information");
		}
		
		return null;
	}

	@Override
	public EnquiryDetails addEnquiryDetails(EnquiryDetails enquiryDetails) {
		
		return er.save(enquiryDetails);
	}

	@Override
	public Users getusers(String userName, String userPassword) {
		
		
		return ur.findAllByUserNameAndUserPassword(userName, userPassword);
	}

	@Override
	public ContactUs addEnquiryDetails(ContactUs contactUs) {
		
		return cr.save(contactUs);
	}

	@Override
	public List<EnquiryDetails> getEnquiry() {
		
		return er.findAll();
	}

	
	

}
