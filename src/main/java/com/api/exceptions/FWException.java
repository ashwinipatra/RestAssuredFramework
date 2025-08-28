package com.api.exceptions;
//custom exceptions2
public class FWException extends RuntimeException {
	public FWException(String message,Throwable e) {
		super(message,e );
	}
	
	public FWException(String message) {
		super(message);
	}
	
}
