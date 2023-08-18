package com.loanapp.main.exceptionresponse;

import java.util.Date;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.loanapp.main.entity.Admin;
import com.loanapp.main.entity.BaseResponse;
import com.loanapp.main.exception.UserNotFoundException;

public class UserNotFoundExceptionHandler {
	@ExceptionHandler(value = UserNotFoundException.class)
	public BaseResponse<Admin> handleUserNotFoundException(){
		return new BaseResponse<Admin>(404, "User Not Found", new Date(), null);
	}
}
