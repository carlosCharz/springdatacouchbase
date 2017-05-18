package com.wedevol.springdatacouchbase.core.exception;

/**
 * Resource Not Found Error Type Enum (404 family)
 *
 * @author charz
 */
public enum NotFoundErrorType {
	USER_NOT_FOUND(404, "User not found");

	private final int code;
	private final String message;

	NotFoundErrorType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
