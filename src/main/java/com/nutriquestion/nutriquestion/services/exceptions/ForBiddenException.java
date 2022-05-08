package com.nutriquestion.nutriquestion.services.exceptions;

public class ForBiddenException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ForBiddenException(String str) {
		super(str);
	}

}
