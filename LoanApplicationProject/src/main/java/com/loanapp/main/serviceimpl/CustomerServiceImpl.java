package com.loanapp.main.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loanapp.main.consts.ApplicationStatus;
import com.loanapp.main.entity.AllPersonalDocuments;
import com.loanapp.main.entity.Customer;
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
			customer.setApplicationStatus(String.valueOf(ApplicationStatus.CREATED));
			customer.getAllPersonalDoc().setAddressProof(addressProof.getBytes());
			customer.getAllPersonalDoc().setPanCard(panCard.getBytes());
			customer.getAllPersonalDoc().setAddharCard(addharCard.getBytes());
			customer.getAllPersonalDoc().setPhoto(photo.getBytes());
			customer.getAllPersonalDoc().setSignature(signature.getBytes());
			customer.getAllPersonalDoc().setSalarySlips(salarySlips.getBytes());
			return cr.save(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<Customer> getCustomerByStatus(String status1, String status2) {
		if (status2.length() < 3) {
			return cr.findAllByApplicationStatus(status1);
		} else {
			return cr.findAllByApplicationStatusOrApplicationStatus(status1, status2);
		}
	}

}
