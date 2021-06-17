package com.learn.sb.onlinebanking.service;

import static java.lang.String.format;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.sb.onlinebanking.constants.ApplicaitonConstants;
import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.entity.BeneficiaryUser;
import com.learn.sb.onlinebanking.entity.TransferFundsRequest;
import com.learn.sb.onlinebanking.exception.TransactionExceptionMessage;
import com.learn.sb.onlinebanking.repository.AccountsRepository;
import com.learn.sb.onlinebanking.repository.BeneficiaryRepository;

@Service
@Transactional
public class TransferFundsService {

	private static final Logger Log = LoggerFactory.getLogger(TransferFundsService.class);

	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	private final Lock acctLock;

	public TransferFundsService()
	{
		acctLock = new ReentrantLock();
	}

	public String transferAmount(TransferFundsRequest transferAccountRequest) {
		Log.info("Retrieving transaction information from TransferAccountRequest  object");
		Long fromAccountNumber = transferAccountRequest.getSourceAccountNumber();
		Long toAccountNumber = transferAccountRequest.getBeneficiaryAccountNumber();
		double amount = transferAccountRequest.getTranferAmt();


		Log.info("transferAmount {}");
		if(accountValidations(fromAccountNumber, toAccountNumber, amount)) {
			Account accountFrom = accountsRepository.findByaccountNubmer(fromAccountNumber);
			BeneficiaryUser accountTo = beneficiaryRepository.findByAccountNumber(toAccountNumber);
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
			getBeneficiaryAccount(accountNumberTo, ApplicaitonConstants.BENEFICIAY_ACCOUNT_NOT_EXIST);
			checkforBeneficiaryToAccountMapping(accountNumberFrom,accountNumberTo,ApplicaitonConstants.BENEFICIARY_NOT_FOUND);
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

	private boolean checkforBeneficiaryToAccountMapping(Long accountNumber, Long benificiaryAccountNumber,String errorReason) {
		Account userAct = accountsRepository.findByaccountNubmer(accountNumber);
		Set<BeneficiaryUser> benificiaryDetails=userAct.getBeneficiaryList();
		if(!benificiaryDetails.isEmpty())
		{
			for(BeneficiaryUser user:benificiaryDetails)

			{
				Long benificiaryAccout=user.getAccountNumber();
				int retVal=benificiaryAccout.compareTo(benificiaryAccountNumber);
				if(retVal == 0)
				{
				return Boolean.TRUE;
				}
			}
			
		}else {
			Log.info(ApplicaitonConstants.BENEFICIARY_NOT_FOUND);
			throw new TransactionExceptionMessage(format(errorReason, accountNumber));
		}
		return Boolean.FALSE;
	}

	private BeneficiaryUser getBeneficiaryAccount(Long accountNumber, String errorReason) {
		BeneficiaryUser userAct = beneficiaryRepository.findByAccountNumber(accountNumber);
		if (userAct == null) {
			Log.info(ApplicaitonConstants.BENEFICIAY_ACCOUNT_NOT_EXIST);
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
	boolean deposit(BeneficiaryUser accountTo,double amountToTransfer)
	{
		try{   
			acctLock.lock();
			accountTo.setAccountBalance(accountTo.getAccountBalance()+ amountToTransfer);
			Log.info("deposit {} : To account balance after deposit " +accountTo.getAccountBalance());

			return true;
		}
		finally{
			acctLock.unlock(); 
		}  

	}


}
