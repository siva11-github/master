package com.learn.sb.onlinebanking.exception;

public class BeneficiaryNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BeneficiaryNotFoundException(final String message) {

		super(message);
	}


}
