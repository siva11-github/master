package com.learn.sb.onlinebanking.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.entity.BeneficiaryUser;
import com.learn.sb.onlinebanking.entity.TransferFundsRequest;
import com.learn.sb.onlinebanking.entity.User;
import com.learn.sb.onlinebanking.exception.TransactionExceptionMessage;
import com.learn.sb.onlinebanking.repository.AccountsRepository;
import com.learn.sb.onlinebanking.repository.BeneficiaryRepository;
import com.learn.sb.onlinebanking.repository.UsersRepository;
import com.learn.sb.onlinebanking.service.AccountService;
import com.learn.sb.onlinebanking.service.BenificiaryService;
import com.learn.sb.onlinebanking.service.TransferFundsService;
import com.learn.sb.onlinebanking.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferFundsServiceTest {
	@InjectMocks
	private AccountService accountsService;

	@InjectMocks
	private UserService userService;

	@InjectMocks
	private BenificiaryService benificiaryService;

	@InjectMocks
	private TransferFundsService transferFundsService;

	@Mock
	private AccountsRepository accountsRepository;
	@Mock
	private UsersRepository userRepository;
	@Mock
	private BeneficiaryRepository beneficiaryRepository;

	Account act;
	User user;

	@Before
	public void setup(){

		accountsService=Mockito.mock(AccountService.class);
		userService=Mockito.mock(UserService.class);
		transferFundsService=Mockito.mock(TransferFundsService.class);
		accountsRepository=Mockito.mock(AccountsRepository.class);
		userRepository=Mockito.mock(UsersRepository.class);
		benificiaryService=Mockito.mock(BenificiaryService.class);
		beneficiaryRepository=Mockito.mock(BeneficiaryRepository.class);

	}


	@Test
	public void negativeAmountTransfer() throws Exception {

		when(accountsRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));
		Account act = accountsService.accountDetailsByAcctNum(100l);
		when(beneficiaryRepository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(2l,1001l,"8989",60000,"siva"));
		BeneficiaryUser bAct = benificiaryService.beneficiaryDetails(1001l);
		TransferFundsRequest transferFundsRequest =new TransferFundsRequest();

		transferFundsRequest.setSourceAccountNumber(act.getAccountNumber());
		transferFundsRequest.setBeneficiaryAccountNumber(bAct.getAccountNumber());
		transferFundsRequest.setTranferAmt(-3000);

		try {
			this.transferFundsService.transferAmount(transferFundsRequest);
		} catch (TransactionExceptionMessage ex) {
			assertThat(ex.getMessage()).isEqualTo("Transaction Amount should be Positive value");
		}
	}

	@Test
	public void providingSourceAccountAsWrong() throws Exception 
	{ 

		when(accountsRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));
		Account act = accountsService.accountDetailsByAcctNum(100l);
		when(beneficiaryRepository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(2l,1001l,"8989",60000,"siva"));
		BeneficiaryUser bAct = benificiaryService.beneficiaryDetails(1001l);
		TransferFundsRequest transferFundsRequest =new TransferFundsRequest();

		transferFundsRequest.setSourceAccountNumber(8989l);
		transferFundsRequest.setBeneficiaryAccountNumber(bAct.getAccountNumber());
		transferFundsRequest.setTranferAmt(3000);
		try {
			this.transferFundsService.transferAmount(transferFundsRequest);
		} catch (TransactionExceptionMessage ex) {
			if(!(transferFundsRequest.getSourceAccountNumber()).equals(act.getAccountNumber()))
				assertThat(ex.getMessage()).isEqualTo("Source account number "+transferFundsRequest.getSourceAccountNumber()+" does not exist.");
			if(!(transferFundsRequest.getBeneficiaryAccountNumber()).equals(bAct.getAccountNumber()))
				assertThat(ex.getMessage()).isEqualTo("Beneficiary account number "+transferFundsRequest.getBeneficiaryAccountNumber()+" does not exist.");
		}
	}


	@Test
	public void providingBeneficiaryAccountAsWrong() throws Exception 
	{ 

		when(accountsRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));
		Account act = accountsService.accountDetailsByAcctNum(100l);
		when(beneficiaryRepository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(2l,1001l,"8989",60000,"siva"));
		BeneficiaryUser bAct = benificiaryService.beneficiaryDetails(1001l);
		TransferFundsRequest transferFundsRequest =new TransferFundsRequest();

		transferFundsRequest.setSourceAccountNumber(act.getAccountNumber());
		transferFundsRequest.setBeneficiaryAccountNumber(878777l);
		transferFundsRequest.setTranferAmt(3000);
		try {
			this.transferFundsService.transferAmount(transferFundsRequest);
		} catch (TransactionExceptionMessage ex) {
			if(!(transferFundsRequest.getSourceAccountNumber()).equals(act.getAccountNumber()))
				assertThat(ex.getMessage()).isEqualTo("Source account number "+transferFundsRequest.getSourceAccountNumber()+" does not exist.");
			if(!(transferFundsRequest.getBeneficiaryAccountNumber()).equals(bAct.getAccountNumber()))
				assertThat(ex.getMessage()).isEqualTo("Beneficiary account number "+transferFundsRequest.getBeneficiaryAccountNumber()+" does not exist.");
		}
	}


	@Test
	public void duplicateAccountsTransfer() throws Exception 
	{ 
		when(accountsRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));
		when(beneficiaryRepository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(2l,1001l,"8989",60000,"siva"));
		BeneficiaryUser bAct = benificiaryService.beneficiaryDetails(1001l);
		TransferFundsRequest transferFundsRequest =new TransferFundsRequest();

		transferFundsRequest.setSourceAccountNumber(bAct.getAccountNumber());
		transferFundsRequest.setBeneficiaryAccountNumber(bAct.getAccountNumber());
		transferFundsRequest.setTranferAmt(3000);
		try {
			this.transferFundsService.transferAmount(transferFundsRequest);
		} catch (TransactionExceptionMessage ex) {
			assertThat(ex.getMessage()).isEqualTo("Source and Destination accounts should not be same");
		}
	}


	@Test
	public void Test_amountTransfer() throws Exception 
	{ 

		when(beneficiaryRepository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(2l,1001l,"8989",60000,"siva"));
		BeneficiaryUser bAct = benificiaryService.beneficiaryDetails(1001l);
		Set blist=new HashSet();
		blist.add(bAct);
		when(accountsRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,blist));
		Account act = accountsService.accountDetailsByAcctNum(100l);

		TransferFundsRequest transferFundsRequest =new TransferFundsRequest();

		transferFundsRequest.setBeneficiaryAccountNumber(bAct.getAccountNumber());
		transferFundsRequest.setSourceAccountNumber(act.getAccountNumber());
		transferFundsRequest.setTranferAmt(1000);


		try {
			assertThat(this.transferFundsService.transferAmount(transferFundsRequest)).isEqualTo("Transaction Sucessfull");
			System.out.println(act.getBalence());
		} catch (TransactionExceptionMessage ex) {
			assertThat(ex.getMessage()).isEqualTo("Transaction got Failed");
		}
	}


	@Test
	public void insufficentBalanceInFromAccount() throws Exception 
	{ 

		when(beneficiaryRepository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(2l,1001l,"8989",60000,"siva"));
		BeneficiaryUser bAct = benificiaryService.beneficiaryDetails(1001l);
		Set blist=new HashSet();
		blist.add(bAct);
		when(accountsRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,blist));
		Account act = accountsService.accountDetailsByAcctNum(100l);

		TransferFundsRequest transferFundsRequest =new TransferFundsRequest();

		transferFundsRequest.setBeneficiaryAccountNumber(bAct.getAccountNumber());
		transferFundsRequest.setSourceAccountNumber(act.getAccountNumber());
		transferFundsRequest.setTranferAmt(500000);


		try {
			assertThat(this.transferFundsService.transferAmount(transferFundsRequest)).isEqualTo("Transaction Sucessfull");
		} catch (TransactionExceptionMessage ex) {
			assertThat(ex.getMessage()).isEqualTo("Account number "+act.getAccountNumber()+" does not have enough balance.");
		}
	}
	
	@Test
	public void test_checkAccountToBeneficiaryMapping() throws Exception 
	{ 

		when(beneficiaryRepository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(2l,1001l,"8989",60000,"siva"));
		BeneficiaryUser bAct = benificiaryService.beneficiaryDetails(1001l);
		when(beneficiaryRepository.findByAccountNumber(1002l)).thenReturn(new BeneficiaryUser(2l,1002l,"8989",60000,"siva"));
		BeneficiaryUser bAct1 = benificiaryService.beneficiaryDetails(1002l);
		Set blist=new HashSet();
		blist.add(bAct);
		when(accountsRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,blist));
		Account act = accountsService.accountDetailsByAcctNum(100l);

		TransferFundsRequest transferFundsRequest =new TransferFundsRequest();

		transferFundsRequest.setBeneficiaryAccountNumber(bAct1.getAccountNumber());
		transferFundsRequest.setSourceAccountNumber(act.getAccountNumber());
		transferFundsRequest.setTranferAmt(1000);
		try {
			this.transferFundsService.transferAmount(transferFundsRequest);
		} catch (TransactionExceptionMessage ex) {
			assertThat(ex.getMessage()).isEqualTo("Entered benificiary account not found in source account.");
		}
	}
}