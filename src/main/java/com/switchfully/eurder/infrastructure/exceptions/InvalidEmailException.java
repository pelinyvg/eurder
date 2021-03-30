package com.switchfully.eurder.infrastructure.exceptions;

public class InvalidEmailException extends Throwable {
    private static final String INVALID_EMAIL = "Invalid Email: ";

    public InvalidEmailException(String message) {
        super(INVALID_EMAIL + message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(INVALID_EMAIL + message, cause);
    }
}
