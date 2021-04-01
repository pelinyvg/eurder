package com.switchfully.eurder.infrastructure.exceptions;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException() {
        super("Customer with the given id does not exist in database!");
    }
}
