package com.demos.demo.comments.exception;

public class ErrorInfo {
	private String code;
	private String message;
	private int statusCode;

	public ErrorInfo(String code, int statusCode, String message) {
		this.code = code;
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
