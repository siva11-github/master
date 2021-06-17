package com.learn.sb.onlinebanking.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.entity.BeneficiaryUser;
import com.learn.sb.onlinebanking.repository.AccountsRepository;
import com.learn.sb.onlinebanking.repository.BeneficiaryRepository;

@Service
public class AccountService {

	@Autowired
	AccountsRepository accountRepository; 

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	public Account findAccount(Long accountNumer) {
		return accountRepository.findByaccountNubmer(accountNumer);
	}

	public void beneficiaryMapping(Account user) {

		Set<BeneficiaryUser> beneficiaryList=user.getBeneficiaryList();
		user.setBeneficiaryList(beneficiaryList);
		accountRepository.save(user);

	}

	public Optional<Account> accountDetails(int actNum) {
		return accountRepository.findById(actNum);
	}

	public Account accountDetailsByAcctNum(Long accountNumber) {
		return accountRepository.findByaccountNubmer(accountNumber);
	}

	public Account accountUpdate(Account act) {
		return accountRepository.save(act);
	}

	public void accountDelete(@Valid int actId) {
		accountRepository.deleteById(actId);
	}


}
