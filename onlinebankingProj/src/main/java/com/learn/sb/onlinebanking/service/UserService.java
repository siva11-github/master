package com.learn.sb.onlinebanking.service;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.entity.User;
import com.learn.sb.onlinebanking.repository.AccountsRepository;
import com.learn.sb.onlinebanking.repository.UsersRepository;

@Transactional
@Service
public class UserService {

	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private UsersRepository usersRepository;
	
	public User userCreation(User user) {
		Account lb =new Account();
		Random random = new Random();
	    Long acctNum = (long) (random.nextInt((9999 - 100) + 1) + 10);
		lb.setAccountNumber(acctNum);
		accountsRepository.save(lb);
		user.setAccount(lb);
		usersRepository.save(user);
		return user;
	}

	public User accountLogin(String email, String pwd) {
		
		return usersRepository.findByEmailAndPassword(email, pwd);
	}

	public User findUserDetails(String email) {
		
		return usersRepository.findByEmail(email);
		
	}


	

	}
