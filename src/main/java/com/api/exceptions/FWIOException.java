package com.api.exceptions;

public class FWIOException extends FWException{
	public FWIOException(String message) {
		super(message);
	}
	public FWIOException(String message,Throwable e) {
		super(message,e);
	}
}
