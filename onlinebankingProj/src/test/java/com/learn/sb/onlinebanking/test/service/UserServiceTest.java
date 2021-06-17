package com.learn.sb.onlinebanking.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import com.learn.sb.onlinebanking.entity.Account;
import com.learn.sb.onlinebanking.entity.User;
import com.learn.sb.onlinebanking.repository.AccountsRepository;
import com.learn.sb.onlinebanking.repository.UsersRepository;
import com.learn.sb.onlinebanking.service.UserService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UsersRepository userreRepository;
	@Mock
	AccountsRepository accoutnRepository;

	User user;
	@Before
	 public void setup(){
		
		userService=Mockito.mock(UserService.class);
		userreRepository=Mockito.mock(UsersRepository.class);
	 
	 }
	
	@Test
	public void userCreationTest()
	{
		User user =new User();
		user.setEmail("shannu@gmail.com");
		user.setPassword("sankar");
		user.setUsername("siva");
		user.setUserId(1);
		Account ac=new Account();
		ac.setAccountId(1);
		ac.setAccountNumber(10008l);
		ac.setBalence(78787);
		user.setAccount(ac);
		User userDetails=userService.userCreation(user);
		assertThat(userDetails.getUsername()).isEqualTo("siva");
		assertThat(userDetails.getPassword()).isEqualTo("sankar");
	}
	
	
	@Test
	public void userLogin_Test()
	{
		
		User user =new User();
		user.setEmail("shannu1@gmail.com");
		user.setPassword("sankar");
		user.setUsername("siva");
		user.setUserId(2);
		Account ac=new Account();
		ac.setAccountId(2);
		ac.setAccountNumber(10008l);
		ac.setBalence(78787);
		user.setAccount(ac);
		User userDetails=userService.userCreation(user);
		User userInfo=userService.accountLogin(userDetails.getEmail(), userDetails.getPassword());
		verify(new ResponseEntity<>(userInfo,HttpStatus.OK));
	}
	
	
	
	

}