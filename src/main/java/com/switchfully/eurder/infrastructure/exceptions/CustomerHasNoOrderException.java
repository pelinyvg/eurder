package com.switchfully.eurder.infrastructure.exceptions;

public class CustomerHasNoOrderException extends Throwable {
    public CustomerHasNoOrderException() {
        super("Customer has no order history!");
    }
}
