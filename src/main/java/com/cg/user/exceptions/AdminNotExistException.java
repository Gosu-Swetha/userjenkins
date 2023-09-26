package com.cg.user.exceptions;

@SuppressWarnings("serial")
public class AdminNotExistException extends RuntimeException{
	
	public AdminNotExistException(String msg) {
		super(msg);
	}
}
