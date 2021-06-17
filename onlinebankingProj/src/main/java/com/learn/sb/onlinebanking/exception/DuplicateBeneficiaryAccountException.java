package com.learn.sb.onlinebanking.exception;

public class DuplicateBeneficiaryAccountException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DuplicateBeneficiaryAccountException(final String message) {

		super(message);
	}


}
