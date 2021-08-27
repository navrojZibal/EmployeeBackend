package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object errorCode;
	
	
	public ControllerException() {}
	public ControllerException(Object errorCode) {
		super();
		this.errorCode = errorCode;
		
	}
	public Object getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Object errorCode) {
		this.errorCode = errorCode;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
