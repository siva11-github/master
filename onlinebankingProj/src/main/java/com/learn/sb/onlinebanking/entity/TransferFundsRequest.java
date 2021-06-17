package com.learn.sb.onlinebanking.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class TransferFundsRequest {
	
	@NotNull
	private Long sourceAccountNumber;
	
	@NotNull
	private Long beneficiaryAccountNumber;
	
	@NotNull
	@Min(value =500, message = "Transfer Amount should be minimum 500")
	private double transferAmt;
	
	public Long getSourceAccountNumber() {
		return sourceAccountNumber;
	}
	public void setSourceAccountNumber(Long sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}
	public Long getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}
	public void setBeneficiaryAccountNumber(Long beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	public double getTranferAmt() {
		return transferAmt;
	}
	public void setTranferAmt(double tranferAmt) {
		this.transferAmt = tranferAmt;
	}
	

}
