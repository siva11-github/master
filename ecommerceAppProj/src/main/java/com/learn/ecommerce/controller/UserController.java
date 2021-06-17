package com.learn.ecommerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dto.UserDto;
import com.learn.ecommerce.entity.User;
import com.learn.ecommerce.exception.UserNotFoundException;
import com.learn.ecommerce.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		User userRequest = modelMapper.map(userDto, User.class);
		User user = userService.userCreation(userRequest);
		UserDto userResponse = modelMapper.map(user, UserDto.class);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<UserDto> login(@RequestParam("email") String email,
										 @RequestParam("password") String pwd) 
	{
		User user= userService.getUserDetails(email,pwd);
		if(user==null) {
			throw new UserNotFoundException("Invalid User");
		}
		UserDto userDto=modelMapper.map(user,UserDto.class);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	

}
