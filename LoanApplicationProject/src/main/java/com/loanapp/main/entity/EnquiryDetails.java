package com.loanapp.main.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eId;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private long mobileNumber;
	private String pancardNumber;
	private String ENQUIRY_STATUS;//CREATED,CIBIL_REQUIRED,CIBIL_OK,CIBIL_REJECT,ACCEPTED
	@OneToOne(cascade=CascadeType.ALL)
	private Cibil cibil;
}
