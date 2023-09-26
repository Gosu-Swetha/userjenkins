package com.cg.user.exceptions;

@SuppressWarnings("serial")
public class InValidEmailException extends RuntimeException{
	
	public InValidEmailException(String msg) {
		super(msg);
	}
}