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
public class Ledger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ledgerId;
	private String ledgerCreatedDate;
	private double totalLoanAmount;
	private double payableAmountwithInterest;
	private int tenure;
	private double monthlyEMI;
	private double amountPaidtillDate;
	private double remainingAmount;
	private String nextEmiDatestart;
	private String nextEmiDateEnd;
	private int defaulterCount;
	private String previousEmitStatus;
	private String currentMonthEmiStatus;
	private String loanEndDate;
	private String loanStatus;
	@OneToOne(cascade=CascadeType.ALL)
	private InstallmentsDetails installmentsDetails;


}
