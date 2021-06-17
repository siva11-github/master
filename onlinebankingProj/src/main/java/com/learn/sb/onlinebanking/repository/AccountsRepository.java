package com.learn.sb.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.sb.onlinebanking.entity.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer>{
	
	
	Account findByaccountNubmer(Long accountNumber);

}
