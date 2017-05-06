package com.wedevol.springdatacouchbase.core.exception;

import java.io.Serializable;

/**
 * Server Error Exception
 *
 * @author Charz++
 */

public class ServerException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	private ErrorType errorType;

	public ServerException(ErrorType errorType) {
		super(errorType.getDescription());
		this.errorType = errorType;
	}

	public ServerException(String message) {
		super(message);
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

}
