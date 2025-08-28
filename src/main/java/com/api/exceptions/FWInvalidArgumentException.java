package com.api.exceptions;

public class FWInvalidArgumentException extends FWException {

	public FWInvalidArgumentException(String message) {
		super(message);
	}
	public FWInvalidArgumentException(String message,Throwable e) {
		super(message,e);
	}
}
