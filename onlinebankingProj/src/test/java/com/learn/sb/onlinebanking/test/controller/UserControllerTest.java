package com.learn.sb.onlinebanking.test.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.sb.onlinebanking.controller.UserController;
import com.learn.sb.onlinebanking.entity.User;
import com.learn.sb.onlinebanking.service.UserService;

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
		user.setUsername("siva");
		user.setUserId(1);
		user.setAccount(null);
		when(userService.userCreation(user)).thenReturn(user);

	}
	}