package com.learn.sb.onlinebanking.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.sb.onlinebanking.constants.ApplicaitonConstants;
import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.exception.AccountNotFoundException;
import com.learn.sb.onlinebanking.exception.BeneficiaryNotFoundException;
import com.learn.sb.onlinebanking.exception.DuplicateUserAccountException;
import com.learn.sb.onlinebanking.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/beneficiaryMapping")
	public ResponseEntity<Object> addBeneficiary(@Valid @RequestBody Account act)
	{
		Account acctDetails=accountService.findAccount(act.getAccountNumber());
		if(acctDetails ==null) 
			throw new DuplicateUserAccountException("User account not Exists: "); 
		else
			accountService.beneficiaryMapping(act);
		return new ResponseEntity<>("done", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getAccount(@RequestParam("accountId") int actId) 
	{
		Optional<Account> act=accountService.accountDetails(actId);
		if(!act.isPresent()) {
			throw new AccountNotFoundException(ApplicaitonConstants.ACCOUNT_NOT_EXIST);
		}
		return new ResponseEntity<>(act,HttpStatus.OK);
	}


	@PutMapping("/")
	public ResponseEntity<Object> updateAccount(@Valid @RequestBody Account act) 
	{ 
		Account actDetails=accountService.accountDetailsByAcctNum(act.getAccountNumber());
		if(actDetails == null)
			throw new BeneficiaryNotFoundException(ApplicaitonConstants.ACCOUNT_NOT_EXIST);
		else
			accountService.accountUpdate(act);
		return new ResponseEntity<>(act,HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAccount(@Valid @RequestParam("accountId") int actId) 
	{
		Optional<Account> act=accountService.accountDetails(actId);
		if(!act.isPresent()) {
			throw new AccountNotFoundException(ApplicaitonConstants.ACCOUNT_NOT_EXIST);
		}
		accountService.accountDelete(actId);
		return new ResponseEntity<>(actId+" Account deleted Sucessfully",HttpStatus.OK);
	}


}
