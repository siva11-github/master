package com.learn.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.entity.User;
import com.learn.ecommerce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository ;
	public User getuser(String email) {
		return userRepository.findByEmail(email);
	}

	public User userCreation(User user) {
		return userRepository.save(user);
		
	}

	public User getUserDetails(String email, String pwd) {
		
		return userRepository.findByEmailAndPassword(email, pwd);
	}

}
