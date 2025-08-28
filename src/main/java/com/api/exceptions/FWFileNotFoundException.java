package com.api.exceptions;

public class FWFileNotFoundException extends FWException{

	public FWFileNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	
	
	public FWFileNotFoundException(String message) {
		super(message);
	}

}
