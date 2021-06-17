package com.learn.sb.onlinebanking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.sb.onlinebanking.entity.User;
import com.learn.sb.onlinebanking.exception.DuplicateUserAccountException;
import com.learn.sb.onlinebanking.exception.InvalidUserAccountException;
import com.learn.sb.onlinebanking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/Registration")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
	{
		User userDetails=userService.findUserDetails(user.getEmail());
		if(userDetails!=null) 
			throw new DuplicateUserAccountException("User details Already Exists: "); 
		else
			userService.userCreation(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<Object> login(@RequestParam("email") String email,
			@RequestParam("password") String pwd) 
	{
		User user= userService.accountLogin(email,pwd);
		if(user ==null) {
			throw new InvalidUserAccountException("Invalid User ");
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
	}




}
