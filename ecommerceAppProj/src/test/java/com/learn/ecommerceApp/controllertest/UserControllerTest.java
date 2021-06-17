package com.learn.ecommerceApp.controllertest;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.ecommerce.controller.UserController;
import com.learn.ecommerce.entity.User;
import com.learn.ecommerce.services.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	private UserController controller;

	@Mock
	private UserService userService;

	@Test
	public void testRegistration() {

		User user =new User();
		user.setEmail("sivasan@gmail.com");
		user.setPassword("sankar");
		when(userService.getUserDetails("sivasan@gmail.com","sankar")).thenReturn(user);

	}
	
	@Test
	public void testLogin() {

		User user =new User();
		user.setEmail("sivasan@gmail.com");
		user.setPassword("sankar");
		when(userService.userCreation(user)).thenReturn(user);

	}


}
