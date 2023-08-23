package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loanapp.main.entity.CustomerAddress;
@Repository
public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, Integer> {

}
