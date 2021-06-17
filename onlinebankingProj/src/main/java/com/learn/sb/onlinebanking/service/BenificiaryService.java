package com.learn.sb.onlinebanking.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.sb.onlinebanking.entity.BeneficiaryUser;
import com.learn.sb.onlinebanking.repository.BeneficiaryRepository;

@Service
public class BenificiaryService {
	
	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	
	
	public void beneficiaryCreation(BeneficiaryUser user) {
		beneficiaryRepository.save(user);
		
	}


	public BeneficiaryUser beneficiaryDetails(Long beneficiaryActNumber) {
		return beneficiaryRepository.findByAccountNumber(beneficiaryActNumber);
		
	}


	public void beneficiaryUpdate(BeneficiaryUser user) {
		 beneficiaryRepository.save(user);
		
	}


	public void beneficiaryDelete(@Valid Long beneficiaryActNumber) {
		beneficiaryRepository.deleteById(beneficiaryActNumber);
		
	}

	

}
