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
import com.learn.sb.onlinebanking.entity.BeneficiaryUser;
import com.learn.sb.onlinebanking.repository.BeneficiaryRepository;
import com.learn.sb.onlinebanking.service.BenificiaryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BenificiaryServiceTest {

	@InjectMocks
	private BenificiaryService benificiaryService;

	@Mock
	BeneficiaryRepository repository;

	@Before
	public void setup(){

		benificiaryService=Mockito.mock(BenificiaryService.class);
		repository=Mockito.mock(BeneficiaryRepository.class);
	}


	@Test
	public void getBeneficiary_Positive()
	{
		when(repository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(1l,1001l,"89898",3000,"siva"));

		BeneficiaryUser bu = benificiaryService.beneficiaryDetails(1001l);
		assertEquals("siva", bu.getBeneficiaryName());
		assertEquals(Double.valueOf(3000), Double.valueOf(bu.getAccountBalance()));
	}

	
	@Test
	public void getBeneficiary_Negative()
	{
		when(repository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(1l,1001l,"89898",3000,"siva"));

		BeneficiaryUser bu = benificiaryService.beneficiaryDetails(1001l);
		assertNotSame("sankar", bu.getBeneficiaryName());
		assertNotSame(Double.valueOf(10000), Double.valueOf(bu.getAccountBalance()));
	}
	
	@Test
	public void deleteBeneficiaryTest_Positive()
	{
		when(repository.findByAccountNumber(1002l)).thenReturn(new BeneficiaryUser(2l,1002l,"89898",3000,"siva"));

		benificiaryService.beneficiaryDelete(2l);
		assertEquals(null,benificiaryService.beneficiaryDetails(2l));
	}


	@Test
	public void deleteBeneficiaryTest_Negative()
	{
		when(repository.findByAccountNumber(1001l)).thenReturn(new BeneficiaryUser(1l,1001l,"89898",3000,"siva"));

		benificiaryService.beneficiaryDelete(1l);
		assertNotSame(new Account(1,100l,30000,null),benificiaryService.beneficiaryDetails(1001l));
	}
}
