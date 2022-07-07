package com.stg.exception;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;

	public CustomException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
}
