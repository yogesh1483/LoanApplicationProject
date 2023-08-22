package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loanapp.main.entity.EnquiryDetails;

public interface EnquiryDetailsRepo extends JpaRepository<EnquiryDetails, Integer>{

	Iterable<EnquiryDetails> findAllByEnquiryStatus(String status1);

	Iterable<EnquiryDetails> findAllByEnquiryStatusOrEnquiryStatus(String status1, String status2);

}
