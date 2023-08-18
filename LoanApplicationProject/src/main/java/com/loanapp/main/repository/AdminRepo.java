package com.loanapp.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	List<Admin> findAllByAusernameAndApassword(String ausername, String apassword);

}
