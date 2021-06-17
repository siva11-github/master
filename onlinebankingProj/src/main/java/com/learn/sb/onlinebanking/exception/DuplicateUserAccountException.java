package com.learn.sb.onlinebanking.exception;

public class DuplicateUserAccountException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DuplicateUserAccountException(final String message) {

		super(message);
	}


}
