package com.wedevol.springdatacouchbase.core.exception;

/**
 * Api not found exception
 * 
 * @author charz
 *
 */
public class ApiException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorType errorStatus;

	public ApiException(ErrorType errorStatus) {
		super(errorStatus.getMessage());
		this.errorStatus = errorStatus;
	}

	@Override
	public Integer getCode() {
		return this.errorStatus.getCode();
	}

}
