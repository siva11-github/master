package com.learn.sb.onlinebanking.test.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.sb.onlinebanking.controller.AccountController;
import com.learn.sb.onlinebanking.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

	@InjectMocks
	private AccountController controller;

	@Mock
	private AccountService accountService;

	@Before
	public void setup(){

		controller=Mockito.mock(AccountController.class);
		accountService=Mockito.mock(AccountService.class);

	}

	


}
