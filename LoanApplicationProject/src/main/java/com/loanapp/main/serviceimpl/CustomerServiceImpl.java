package com.loanapp.main.serviceimpl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.consts.ApplicationStatus;
import com.loanapp.main.consts.Currentloanstatus;
import com.loanapp.main.entity.Customer;
import com.loanapp.main.entity.CustomerAddress;
import com.loanapp.main.entity.EnquiryDetails;
import com.loanapp.main.repository.CustomerRepository;
import com.loanapp.main.servicei.CustomerServiceI;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

	@Autowired
	CustomerRepository cr;

	@Override
	public Customer setCustomer(Customer customer, MultipartFile addressProof, MultipartFile panCard,
			MultipartFile addharCard, MultipartFile photo, MultipartFile signature, MultipartFile salarySlips) {
		try {
			//Customer All Personal Documents
			customer.setApplicationStatus(String.valueOf(ApplicationStatus.CREATED));
			customer.getAllPersonalDoc().setAddressProof(addressProof.getBytes());
			customer.getAllPersonalDoc().setPanCard(panCard.getBytes());
			customer.getAllPersonalDoc().setAddharCard(addharCard.getBytes());
			customer.getAllPersonalDoc().setPhoto(photo.getBytes());
			customer.getAllPersonalDoc().setSignature(signature.getBytes());
			customer.getAllPersonalDoc().setSalarySlips(salarySlips.getBytes());
			
			double customerMobileNumber = customer.getCustomerMobileNumber();
			EnquiryDetails e = cr.findByCustomerMobileNumber(customerMobileNumber);
			customer.getCibilScore().setCibilScore(e.getCibil().getCibilScore());
			customer.getCibilScore().setCibilScoreDateTime(String.valueOf(new Date()));
			customer.getCibilScore().setCibilStatus(e.getCibil().getCibilStatus());
			customer.getCibilScore().setCibilRemark(e.getCibil().getCibilRemark());
			customer.getCurrentLoanDetails().setCurrentLoanNumber(e.getEid());
			
			customer.getCurrentLoanDetails().setSanctionDate(String.valueOf(new Date()));
			
			 Date d=new Date();
			 
			 Calendar cale = Calendar.getInstance();
		        cale.setTime(d);
		        cale.add(Calendar.DATE, 30);
		     Date pd = cale.getTime();
			
		   customer.getCurrentLoanDetails().getEmiDetails().setNextEmiDueDate(String.valueOf(pd));
		   customer.setApplicationStatus(Currentloanstatus.INPROCESS.toString());
		   customer.getCurrentLoanDetails().setRemark("OK");
		   customer.getCurrentLoanDetails().setStatus(Currentloanstatus.INPROCESS.toString());
		
		   return cr.save(customer);
		  } 
		  catch (IOException e) {
			e.printStackTrace();
	    }
		return null;
	}

	//FOR GETTING CUSTOMER DETAILS
	@Override
	public Iterable<Customer> getCustomerByStatus(String status1, String status2) {
		if (status2.length() < 3) {
			return cr.findAllByApplicationStatus(status1);
		} else {
			return cr.findAllByApplicationStatusOrApplicationStatus(status1, status2);
		}
	}

	//FOR UPDATING CUSTOMER DETAILS
	@Override
	public Customer updateCustomerBycustomerId(int customerId, Customer c) {
		
		Customer cust = cr.findByCustomerId(customerId);
		String s = cust.getApplicationStatus();
	
		if(cust.getApplicationStatus().equals("INPROCESS"))
		{
			System.out.println(cust);
			cust.getCurrentLoanDetails().setStatus(Currentloanstatus.VERIFICATION_STATE.toString());
			cust.setApplicationStatus(Currentloanstatus.VERIFICATION_STATE.toString());
			cr.save(cust);
			return cust;
		}
		else if(cust.getApplicationStatus().equals("VERIFICATION_STATE"))
		{
			cust.getCurrentLoanDetails().setStatus(Currentloanstatus.VERIFICATION_DONE.toString());
			cust.getCustomerverification().setVerificationDate(String.valueOf(new Date()));
			cust.getCustomerverification().setRemarks("Verified");
			cust.setApplicationStatus(Currentloanstatus.VERIFICATION_DONE.toString());
			cust.getCustomerverification().setStatus(Currentloanstatus.VERIFICATION_DONE.toString());
			cr.save(cust);
			return cust;
		}
		else if(cust.getApplicationStatus().equals("VERIFICATION_DONE"))
		{
			cust.setApplicationStatus(Currentloanstatus.SANCTIONED.toString());
			cust.getCurrentLoanDetails().setStatus(Currentloanstatus.SANCTIONED.toString());
			cust.getSanctionLetter().setSanctionDate(String.valueOf(new Date()));
			cust.getSanctionLetter().setApplicantName(cust.getCustomerName());
			cust.getSanctionLetter().setContactDetails(cust.getCustomerMobileNumber());
			cust.getSanctionLetter().setLoanAmtSanctioned(cust.getCurrentLoanDetails().getLoanAmount());
			cust.getSanctionLetter().setRateOfInterest(cust.getCurrentLoanDetails().getRateOfInterest());
			cust.getSanctionLetter().setLoanTenure(cust.getCurrentLoanDetails().getTenure());
			cust.getSanctionLetter().setMonthlyEmiAmount(cust.getCurrentLoanDetails().getEmiDetails().getEmiAmountMonthlyl());
			cust.getSanctionLetter().setSanctionStatus(Currentloanstatus.SANCTIONED.toString());
			cust.getCurrentLoanDetails().setStatus(Currentloanstatus.SANCTIONED.toString());
			
			cr.save(cust);
			return cust;
		}
		else if(cust.getApplicationStatus().equals("SANCTIONED"))
		{
			cust.setApplicationStatus(Currentloanstatus.DISBURSED.toString());

			cust.getCurrentLoanDetails().setStatus(Currentloanstatus.DISBURSED.toString());
			cust.getCurrentLoanDetails().getCurrentLoanNumber();
		
			cust.getLoanDisbursement().setAgreementDate(String.valueOf(new Date()));
			cust.getLoanDisbursement().setTotalAmount(cust.getSanctionLetter().getLoanAmtSanctioned());
			cust.getLoanDisbursement().setBankName(cust.getAccountDetails().getBankname());
			cust.getLoanDisbursement().setAccountNumber(cust.getAccountDetails().getAccountNumber());
			cust.getLoanDisbursement().setTransferAmount(cust.getSanctionLetter().getLoanAmtSanctioned());
			cust.getLoanDisbursement().setAmountPaidDate(String.valueOf(new Date()));
			
			cr.save(cust);
			return cust;
			
		}
		else if(cust.getApplicationStatus().equals("DISBURSED"))
		{
			
			cust.getLedger().setLedgerCreatedDate(String.valueOf(new Date()));
			cust.getLedger().setTotalLoanAmount(cust.getSanctionLetter().getLoanAmtSanctioned());
			cust.getLedger().setPayableAmountwithInterest(cust.getCurrentLoanDetails().getTotalAmountToBePaidDouble());
			cust.getLedger().setTenure(cust.getCurrentLoanDetails().getTenure());
			cust.getLedger().setMonthlyEMI(cust.getCurrentLoanDetails().getEmiDetails().getEmiAmountMonthlyl());	
	    }
		
		return null;
		
	}

}
