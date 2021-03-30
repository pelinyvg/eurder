package com.switchfully.eurder.infrastructure.exceptions;

public class InvalidPhoneNumberException extends Throwable {
    private static final String INVALID_PHONE = "Invalid Phone Number: ";
    public InvalidPhoneNumberException(String message) {
        super(INVALID_PHONE + message);
    }

    public InvalidPhoneNumberException(String message, Throwable cause) {
        super(INVALID_PHONE + message, cause);
    }
}
