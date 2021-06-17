package com.learn.sb.onlinebanking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.sb.onlinebanking.constants.ApplicaitonConstants;
import com.learn.sb.onlinebanking.entity.BeneficiaryUser;
import com.learn.sb.onlinebanking.exception.BeneficiaryNotFoundException;
import com.learn.sb.onlinebanking.service.BenificiaryService;


@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {
	
	@Autowired
	BenificiaryService benificiaryService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getBeneficiary(@Valid @RequestParam Long beneficiaryActNumber) 
	{ 
		BeneficiaryUser beneficiaryUser =benificiaryService.beneficiaryDetails(beneficiaryActNumber);
		if(beneficiaryUser==null)
			throw new BeneficiaryNotFoundException(ApplicaitonConstants.BENEFICIARY_NOT_EXIST);
		return new ResponseEntity<>(beneficiaryUser,HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateBeneficiary(@Valid @RequestBody BeneficiaryUser user) 
	{ 
		BeneficiaryUser beneficiaryUser =benificiaryService.beneficiaryDetails(user.getBeneficiaryId());
		if(beneficiaryUser==null)
			throw new BeneficiaryNotFoundException(ApplicaitonConstants.BENEFICIARY_NOT_EXIST);
		else
			benificiaryService.beneficiaryUpdate(user);
		return new ResponseEntity<>(beneficiaryUser,HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBeneficiary(@Valid @RequestParam Long beneficiaryActNumber) 
	{ 
		BeneficiaryUser beneficiaryUser =benificiaryService.beneficiaryDetails(beneficiaryActNumber);
		if(beneficiaryUser==null)
			throw new BeneficiaryNotFoundException(ApplicaitonConstants.BENEFICIARY_NOT_EXIST);
		else
			benificiaryService.beneficiaryDelete(beneficiaryActNumber);
		return new ResponseEntity<>(beneficiaryUser,HttpStatus.OK);
	}
}
