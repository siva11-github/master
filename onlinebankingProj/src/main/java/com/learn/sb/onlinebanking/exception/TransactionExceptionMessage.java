package com.learn.sb.onlinebanking.exception;

public class TransactionExceptionMessage extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionExceptionMessage(String message) {
		super(message);
	}

}
