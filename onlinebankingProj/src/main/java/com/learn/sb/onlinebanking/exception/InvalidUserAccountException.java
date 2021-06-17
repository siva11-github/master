package com.learn.sb.onlinebanking.exception;

public class InvalidUserAccountException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidUserAccountException(final String message) {

		super(message);
	}


}
