package com.nutriquestion.nutriquestion.services.exceptions;

public class UnauthorizedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String str) {
		super(str);
	}

}
