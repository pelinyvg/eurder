package com.switchfully.eurder.domain.users.customers;

import com.switchfully.eurder.domain.users.User;
import com.switchfully.eurder.domain.users.UserRole;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import com.switchfully.eurder.infrastructure.util.ValidationUtil;

public class Customer extends User {
    private final Address address;
    private final PhoneNumber phoneNumber;

    public Customer(String firstName, String lastName, String emailAddress, Address address, PhoneNumber phoneNumber)
            throws InvalidEmailException, InvalidPhoneNumberException {
        super(firstName, lastName, emailAddress, UserRole.CUSTOMER);
        ValidationUtil.throwExceptionIfNull(address, "Address");
        this.address = address;
        ValidationUtil.throwExceptionIfNull(phoneNumber, "Phone number");
        if (ValidationUtil.isPhoneNumberValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidPhoneNumberException("The given phone number is not in the correct format");
        }
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
