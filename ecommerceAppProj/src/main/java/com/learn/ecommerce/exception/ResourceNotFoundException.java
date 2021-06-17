package com.learn.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(final String message) {
		super(message);
	}

}
