package com.nutriquestion.nutriquestion.resources.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthCustomError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String error;
	@JsonProperty("error_description")
	private String errorDesciption;
	
	public OAuthCustomError() {}

	public OAuthCustomError(String error, String errorDesciption) {
		this.error = error;
		this.errorDesciption = errorDesciption;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDesciption() {
		return errorDesciption;
	}

	public void setErrorDesciption(String errorDesciption) {
		this.errorDesciption = errorDesciption;
	}
	
}
