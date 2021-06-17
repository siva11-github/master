package com.learn.ecommerceApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.ecommerce.entity.User;
import com.learn.ecommerce.repository.UserRepository;
import com.learn.ecommerce.services.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userreRepository;

	User user;
	
	@Before
	 public void setup(){
		
		userService=Mockito.mock(UserService.class);
		userreRepository=Mockito.mock(UserRepository.class);
	 
	 }
	@Test
	public void userCreationTest()
	{
		User user =new User();
		user.setEmail("shannu@gmail.com");
		user.setPassword("sankar");
		user.setName("siva");
		user.setId(1);
		when(userreRepository.save(user)).thenReturn(user);
		User userDetails=userService.userCreation(user);
		assertThat(userDetails.getName()).isEqualTo("siva");
		assertThat(userDetails.getPassword()).isEqualTo("sankar");
	}
	
	
	@Test
	public void userLogin_Test()
	{
		
		User user =new User();
		user.setEmail("shannu1@gmail.com");
		user.setPassword("sankar");
		user.setMobileNum("98989898");
		when(userreRepository.findByEmailAndPassword("shannu1@gmail.com","sankar")).thenReturn(user);
		assertEquals("98989898",user.getMobileNum());
	}
	
	
	
	

}
