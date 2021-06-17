package com.learn.sb.onlinebanking.test.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.repository.AccountsRepository;
import com.learn.sb.onlinebanking.service.AccountService;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AccountsServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private AccountsRepository accoutnRepository;

	@Before
	public void setup(){

		accountService=Mockito.mock(AccountService.class);
		accoutnRepository=Mockito.mock(AccountsRepository.class);

	}

	@Test
	public void getAccountTest_Positive()
	{
		when(accoutnRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));

		Account act = accountService.accountDetailsByAcctNum(100l);

		assertEquals(1, act.getAccountId());
		assertEquals(null, act.getBeneficiaryList());
	}

	@Test
	public void getAccountTest_Negative()
	{
		when(accoutnRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));

		Account act = accountService.accountDetailsByAcctNum(100l);

		assertNotSame(10, act.getAccountId());
		assertNotSame(100, act.getBeneficiaryList());
	}

	@Test
	public void deleteAccountTest_Positive()
	{
		when(accoutnRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));

		accountService.accountDelete(1);
		assertEquals(null,accountService.accountDetails(1));
	}


	@Test
	public void deleteAccountTest_Negative()
	{
		when(accoutnRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,30000,null));

		accountService.accountDelete(1);
		assertNotSame(1,accountService.accountDetails(1));
	}

	@Test
	public void updateAccountTest_Positive()
	{
		when(accoutnRepository.findByaccountNubmer(100l)).thenReturn(new Account(1,100l,10000,null));
		Account acct = accoutnRepository.findByaccountNubmer(100l);
		acct.setBalence(30000);
		accoutnRepository.save(acct);
		Account updatedAct=accoutnRepository.findByaccountNubmer(100l);
		System.out.println(updatedAct.getBalence());
		assertEquals(Double.valueOf(30000), Double.valueOf(updatedAct.getBalence()));
	}


}
