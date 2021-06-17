package com.learn.sb.onlinebanking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class User {

@Id  
@GeneratedValue(strategy=GenerationType.AUTO)  
private int userId;  

@Column
@NotNull
@NotBlank(message = "Please enter the User Name")
private String username;  

@Column
@NotNull
@NotBlank(message = "Please enter the Email")
@Email
private String email;

@Column
@NotNull
@NotBlank(message = "Please enter the Password")
private String password;

public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public User()
{
	
}
public User(int userId, @NotNull @NotBlank(message = "Please enter the User Name") String username,
		@NotNull @NotBlank(message = "Please enter the Email") @Email String email,
		@NotNull @NotBlank(message = "Please enter the Password") String password, Account account) {
	super();
	this.userId = userId;
	this.username = username;
	this.email = email;
	this.password = password;
	this.account = account;
}
public void setPassword(String password) {
	this.password = password;
}

@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_account",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "account_id")}
  )
@ApiModelProperty(hidden=true)
private Account account;

public Account getAccount() {
	return account;
}
public void setAccount(Account account) {
	this.account = account;
} 
}

