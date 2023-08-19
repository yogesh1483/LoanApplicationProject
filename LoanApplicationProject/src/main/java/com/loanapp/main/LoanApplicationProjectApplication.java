package com.loanapp.main;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoanApplicationProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoanApplicationProjectApplication.class, args);
	}
	
	@Bean
	public Random rm()
	{
		Random rn=new Random();
		return rn;
		
	}
}
