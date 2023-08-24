package com.loanapp.main;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
	
	@Bean
	public RestTemplate rs(){
		RestTemplate rt = new RestTemplate();
		return rt;
	}
}
