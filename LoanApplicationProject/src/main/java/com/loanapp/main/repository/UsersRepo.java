package com.loanapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanapp.main.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Integer>{
	
	Users findAllByUserNameAndUserPassword(String userName,String userPassword);

}
