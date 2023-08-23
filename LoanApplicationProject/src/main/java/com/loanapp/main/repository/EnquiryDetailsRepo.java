package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loanapp.main.entity.EnquiryDetails;

public interface EnquiryDetailsRepo extends JpaRepository<EnquiryDetails, Integer>{

}
