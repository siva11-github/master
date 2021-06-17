package com.learn.sb.onlinebanking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Beneficiaries")
public class BeneficiaryUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long beneficiaryId;
	
	@Column
	private Long accountNumber;
	
	@Column
	private String ifscCode;
	
	@Column
	private double accountBalance;
	
	@Column
	private String beneficiaryName;

	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	
	public BeneficiaryUser()
	{
		
	}

	public BeneficiaryUser(Long beneficiaryId, Long accountNumber, String ifscCode, double accountBalance,
			String beneficiaryName) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.accountBalance = accountBalance;
		this.beneficiaryName = beneficiaryName;
	}
	
	
	
}
