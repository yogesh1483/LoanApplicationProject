package com.loanapp.main.exceptionresponse;

import java.util.Date;

import org.springframework.web.bind.annotation.ExceptionHandler;
import com.loanapp.main.entity.BaseResponse;
import com.loanapp.main.entity.Users;
import com.loanapp.main.exception.UserNotFoundException;

public class UserNotFoundExceptionHandler {
	@ExceptionHandler(value = UserNotFoundException.class)
	public BaseResponse<Users> handleUserNotFoundException(){
		return new BaseResponse<Users>(404, "User Not Found", new Date(), null);
	}
}
