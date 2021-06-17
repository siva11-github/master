package com.learn.sb.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.sb.onlinebanking.entity.User;


@Repository
public interface UsersRepository extends JpaRepository<User, Integer>{
	
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);

}
