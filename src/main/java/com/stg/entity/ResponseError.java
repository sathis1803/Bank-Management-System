package com.stg.entity;

public class ResponseError {

	private String errorMessage;
	private String requestedURI;

	public ResponseError() {
		super();
	}

	public ResponseError(String errorMessage, String requestedURI) {
		super();
		this.errorMessage = errorMessage;
		this.requestedURI = requestedURI;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestedURI() {
		return requestedURI;
	}

	public void setRequestedURI(String requestedURI) {
		this.requestedURI = requestedURI;
	}

}
