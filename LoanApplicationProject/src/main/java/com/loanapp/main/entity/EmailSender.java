package com.loanapp.main.entity;

//import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmailSender {
	private String mailFrom;
	private String mailTo;
	private String mailSubject;
	private String mailText;
	}
