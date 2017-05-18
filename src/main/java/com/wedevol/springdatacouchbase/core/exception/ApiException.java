package com.wedevol.springdatacouchbase.core.exception;

/**
 * Api not found exception
 * 
 * @author charz
 *
 */
public class ApiException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiException(ErrorType errorType) {
		super(errorType.getCode(), errorType.getMessage());
	}

}
