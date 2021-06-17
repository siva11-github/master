package com.learn.sb.onlinebanking.service;

import static java.lang.String.format;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.sb.onlinebanking.constants.ApplicaitonConstants;
import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.entity.User;
import com.learn.sb.onlinebanking.exception.TransactionExceptionMessage;
import com.learn.sb.onlinebanking.repository.AccountsRepository;
import com.learn.sb.onlinebanking.repository.UsersRepository;

@Service
@Transactional
public class FundService {

	private static final Logger Log = LoggerFactory.getLogger(TransferFundsService.class);

	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	UsersRepository usersRepository;
	private final Lock acctLock;

	public FundService()
	{
		acctLock = new ReentrantLock();
	}

	public String transferAmount(long fromAccountNumber, long toAccountNumber, double amount) {
		Log.info("Retrieving transaction information from TransferAccountRequest  object");
		Log.info("transferAmount {}")
		;
		if(accountValidations(fromAccountNumber, toAccountNumber, amount)) {
			Account accountFrom = accountsRepository.findByaccountNubmer(fromAccountNumber);
			Account accountTo = accountsRepository.findByaccountNubmer(toAccountNumber);
			try
			{
				if(withdrawal(accountFrom,amount)){
					if(deposit(accountTo,amount)) {
						Log.info(ApplicaitonConstants.TRANSACTION_SUCESSFULL);
					}
					else
						throw new TransactionExceptionMessage(format(ApplicaitonConstants.TRANSACTION_FAILED));
				}
			} catch (Exception e) {throw new TransactionExceptionMessage(format(ApplicaitonConstants.TRANSACTION_FAILED));}
		}
		else
			return ApplicaitonConstants.TRANSACTION_FAILED;
		return ApplicaitonConstants.TRANSACTION_SUCESSFULL;

	}

	public boolean accountValidations(Long accountNumberFrom, Long  accountNumberTo, double amountToTransfer)
	{
		if(amountToTransfer>=1) {
			if(accountNumberFrom.compareTo(accountNumberTo)==0)
			{
				Log.info(ApplicaitonConstants.ACCOUNT_MATCH);
				throw new TransactionExceptionMessage(format(ApplicaitonConstants.ACCOUNT_MATCH));
			}
			getSourceAccount(accountNumberFrom, ApplicaitonConstants.SOURCE_ACCOUNT_NOT_EXIST);
			getToAccount(accountNumberTo, ApplicaitonConstants.TO_ACCOUNT_NOT_EXIST);
			if(checkBalance(accountNumberFrom) < amountToTransfer){
				Log.info(ApplicaitonConstants.NOT_ENOUGH_BALANCE);
				throw new TransactionExceptionMessage(format(ApplicaitonConstants.NOT_ENOUGH_BALANCE, accountNumberFrom));}
			return Boolean.TRUE;
		}
		else {
			throw new TransactionExceptionMessage(format(ApplicaitonConstants.TRANS_AMOUNT_VALUE,amountToTransfer));
		}
	}

	private Account getSourceAccount(Long accountNumber, String errorReason) {
		Account userAct = accountsRepository.findByaccountNubmer(accountNumber);
		if (userAct == null) {
			Log.info(ApplicaitonConstants.SOURCE_ACCOUNT_NOT_EXIST);
			throw new TransactionExceptionMessage(format(errorReason, accountNumber));
		}
		return userAct;
	}


	private Account getToAccount(Long accountNumber, String errorReason) {
		Account userAct = accountsRepository.findByaccountNubmer(accountNumber);

		if (userAct == null) {
			Log.info(ApplicaitonConstants.TO_ACCOUNT_NOT_EXIST);
			throw new TransactionExceptionMessage(format(errorReason, accountNumber));
		}
		return userAct;
	}

	public double checkBalance(Long accountNumberFrom){
		try {
			acctLock.lock();
			Account accountFrom = accountsRepository.findByaccountNubmer(accountNumberFrom);
			return accountFrom.getBalence();
		}
		finally {
			acctLock.unlock();
		}
	}

	boolean withdrawal(Account accountFrom,double amountToTransfer)
	{ 
		try{   
			acctLock.lock();
			if(accountFrom.getBalence()>=amountToTransfer){
				accountFrom.setBalence(accountFrom.getBalence()-amountToTransfer);
				Log.info("withdrawal {} : Frome account balance after withdrawal " +accountFrom.getBalence());
				return true;
			}
			else {
				Log.info("withdrawal {} : withdrawal Failed");
				return false;}}
		finally{
			acctLock.unlock(); 
		}

	}
	boolean deposit(Account accountTo,double amountToTransfer)
	{
		try{   
			acctLock.lock();
			accountTo.setBalence(accountTo.getBalence()+ amountToTransfer);
			Log.info("deposit {} : To account balance after deposit " +accountTo.getBalence());

			return true;
		}
		finally{
			acctLock.unlock(); 
		}  

	}

	public String transferAmount(String fromEmail, String toEmail, double balence) {

		User fromUser=usersRepository.findByEmail(fromEmail);
		User toUser=usersRepository.findByEmail(toEmail);
		if(fromUser==null)
			return "Invalid From account";
		Account fromAct=fromUser.getAccount();
		if(toUser==null)	
			return "Invalid To account";
		Account toAct=toUser.getAccount();
		return transferAmount(fromAct.getAccountNumber(),toAct.getAccountNumber(), balence);
	}




}
