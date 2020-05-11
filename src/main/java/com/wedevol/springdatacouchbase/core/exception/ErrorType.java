package com.wedevol.springdatacouchbase.core.exception;

/**
 * Api Error Type Enum
 *
 * @author charz
 */
public enum ErrorType {
    USER_NOT_FOUND(1, "User not found"),
    USER_ALREADY_EXISTS(2, "User already exists"),
    CAR_NOT_FOUND(3, "Car not found"),
    PRODUCT_NOT_FOUND(4, "Product not found"),
    PLACE_NOT_FOUND(5, "Place not found"),
    PHONE_NOT_FOUND(6, "Phone not found");

    private final int code;
    private final String message;

    private ErrorType(int code, String message) {
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
