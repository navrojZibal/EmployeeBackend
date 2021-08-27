package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class BuisnessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object errorCode;
	
	
	public BuisnessException() {
	}
	public BuisnessException(Object errorCode) {
		super();
		this.errorCode = errorCode;
		
	}
	public Object getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Object errorCode) {
		this.errorCode = errorCode;
	}
	
	
	@Override
	public String toString() {
		return "BuisnessException [errorCode=" + errorCode;
	}
	
	
	
}
