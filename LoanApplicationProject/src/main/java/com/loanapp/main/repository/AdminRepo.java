package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

}
