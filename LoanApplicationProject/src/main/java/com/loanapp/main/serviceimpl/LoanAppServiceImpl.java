package com.loanapp.main.serviceimpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.loanapp.main.consts.CibilStatus;
import com.loanapp.main.consts.EnquiryStatus;
import com.loanapp.main.entity.ContactUs;
import com.loanapp.main.entity.CurrentLoanDetails;
import com.loanapp.main.entity.CustomerAddress;
import com.loanapp.main.entity.CustomerVerification;
import com.loanapp.main.entity.EmailSender;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserCanNotCreatedException;
import com.loanapp.main.repository.CibilRepo;
import com.loanapp.main.repository.ContactUsRepo;
import com.loanapp.main.repository.CurrentDetailsRepo;
import com.loanapp.main.repository.CustomerAddressRepo;
import com.loanapp.main.repository.CustomerVerificationRepo;
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
	@Autowired
	JavaMailSender sender;
	@Autowired
	CurrentDetailsRepo cld;
	@Autowired
	CustomerAddressRepo car;
	@Autowired
	CustomerVerificationRepo cvr;
	@Autowired
	CibilRepo cibilRepo;
	@Value("$spring.mail.username")
	private String formMail;
	@Override
	public Users saveUser(Users user, MultipartFile profileImg) throws UserCanNotCreatedException {
		if (user.getUserType() != null) {
			try {
				user.setProfileImg(profileImg.getBytes());
				user.setUserName(user.getName() + "" + user.getUserType() + "@" + rm.nextInt(4444));
				user.setUserPassword(user.getUserType() + "@" + rm.nextInt(1111));
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setFrom(formMail);
				simpleMailMessage.setTo(user.getUserEmail());
				simpleMailMessage.setSubject("Loan Application Username And Password");
				simpleMailMessage.setText("Hello" + user.getName() + "\t Your Username: " + user.getUserName()
						+ "/t And Password: " + user.getUserPassword());
				sender.send(simpleMailMessage);
				return ur.save(user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new UserCanNotCreatedException("User Can Not Be Created Provide Valid Information");
		}
		return null;
	}

	@Override
	public EnquiryDetails addEnquiryDetails(EnquiryDetails enquiryDetails) {

		enquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.CREATED));

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(formMail);
		simpleMailMessage.setTo(enquiryDetails.getEmail());
		simpleMailMessage.setSubject("You have filled Enquiry Form..Thank you!!");
		simpleMailMessage.setText("Hello " + enquiryDetails.getFirstName() + "\t" + enquiryDetails.getLastName()
				+ "/t And Your Enquiry Status is: " + enquiryDetails.getEnquiryStatus());
		sender.send(simpleMailMessage);
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

	// for adding current loan details
	@Override
	public CurrentLoanDetails addCurrentLoanDetails(CurrentLoanDetails currentLoanDetails) {
		return cld.save(currentLoanDetails);
	}

	// for adding Customer Address details
	@Override
	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress) {
		return car.save(customerAddress);
	}

	// for adding Customer Verification details
	@Override
	public CustomerVerification addCustomerVerification(CustomerVerification customerVerification) {
		return cvr.save(customerVerification);
	}

	@Override
	public Iterable<EnquiryDetails> getEnquiryOnStatus(String status1, String status2) {
		if (status2.length() < 3) {
			return er.findAllByEnquiryStatus(status1);
		} else {
			return er.findAllByEnquiryStatusOrEnquiryStatus(status1, status2);
		}
	}

	@Override
	public EnquiryDetails updateEnquiryStatus(int eId, int cibilScore, EnquiryDetails updatedEnquiryDetails) {
		Optional<EnquiryDetails> e =er.findById(eId);
		if(e.isPresent())
		{
		updatedEnquiryDetails.getCibil().setCibilScore(cibilScore);
		String date = LocalDateTime.now().toString();
		updatedEnquiryDetails.getCibil().setCibilScoreDateTime(date);
		String enquiryStatus = updatedEnquiryDetails.getEnquiryStatus();
		if (enquiryStatus.equals("CREATED")) {
			updatedEnquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_REQUIRED));
			updatedEnquiryDetails.getCibil().setCibilStatus(String.valueOf(CibilStatus.GOOD_CIBIL));
			return er.save(updatedEnquiryDetails);
		}
		else if(enquiryStatus.equals("CIBIL_REQUIRED")) {
			updatedEnquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_CHECKED));
			updatedEnquiryDetails.getCibil().setCibilStatus(String.valueOf(CibilStatus.GOOD_CIBIL));
			return er.save(updatedEnquiryDetails);
		}
		else if(enquiryStatus.equals("CIBIL_CHECKED")) {
			if(cibilScore>650) {
				updatedEnquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_ARROVED));
				updatedEnquiryDetails.getCibil().setCibilStatus(String.valueOf(CibilStatus.GOOD_CIBIL));
				return er.save(updatedEnquiryDetails);
			}
			else {
				updatedEnquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_REJECT));
				updatedEnquiryDetails.getCibil().setCibilStatus(String.valueOf(CibilStatus.GOOD_CIBIL));
				return er.save(updatedEnquiryDetails);
			}
		}
		}
		return  null;
	}
	
	@Override
	public EmailSender sendMail(EmailSender emailSender, Users user) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(formMail);
		simpleMailMessage.setTo(emailSender.getMailTo());
		simpleMailMessage.setSubject("You have filled Enquiry Form..Thank you!!");
		simpleMailMessage.setText("Hello "); 
		sender.send(simpleMailMessage); 

		return null;
	}

	@Override
	public List<Users> getAllUsers() {
		
		return ur.findAll();
	}
	
}