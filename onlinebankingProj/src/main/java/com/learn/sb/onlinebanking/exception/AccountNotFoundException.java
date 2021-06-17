package com.learn.sb.onlinebanking.exception;

public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(final String message) {

		super(message);
	}


}
