package com.learn.sb.onlinebanking.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Account {
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  

	private int accountId;  
	private Long accountNubmer;  
	private double balence;

	public double getBalence() {
		return balence;
	}

	public void setBalence(double balence) {
		this.balence = balence;
	}

	public Account(int accountId, Long accountNumer, double balence, Set<BeneficiaryUser> beneficiaryUser) {
		super();
		this.accountId = accountId;
		this.accountNubmer = accountNumer;
		this.balence = balence;
		this.beneficiaryList = beneficiaryUser;
	}


	public Account() {  
		super();  

	}  
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinTable(name = "Account_Beneficiary",
	          joinColumns = {@JoinColumn(name = "USER_ACCOUNT")},
	          inverseJoinColumns = {@JoinColumn(name = "BENEFICIARY_ACCOUNT")}
	  )
	//@ApiModelProperty(hidden=true)
	 private Set<BeneficiaryUser> beneficiaryList;
	
	
	public void setBeneficiaryList(Set<BeneficiaryUser> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}

	public Set<BeneficiaryUser> getBeneficiaryList() {
		return beneficiaryList;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Long getAccountNumber() {
		return accountNubmer;
	}

	public void setAccountNumber(Long accountNumer) {
		this.accountNubmer = accountNumer;
	}

}
