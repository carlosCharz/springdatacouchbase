package com.wedevol.springdatacouchbase.core.exception;

/**
 * Error types description
 *
 * @author Charz++
 */

public enum ErrorType {
	ILLEGAL_RESULT_LENGTH(0, "The lenght of the result list is not appropriate.");

	private int code;
	private String description;

	private ErrorType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}
