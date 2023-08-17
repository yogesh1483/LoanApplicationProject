package com.loanapp.main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EMIDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int emiID;
	private double emiAmountMonthlyl;
	private String nextEmiDueDate;
	private String previousEmiStatus;
}
