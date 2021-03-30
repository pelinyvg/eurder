package com.switchfully.eurder.domain;

import com.switchfully.eurder.infrastructure.util.ValidationUtil;

public class Customer extends User {
    private final Address address;
    private final PhoneNumber phoneNumber;

    public Customer(String firstName, String lastName, String emailAddress, UserRole role, Address address, PhoneNumber phoneNumber) {
        super(firstName, lastName, emailAddress, role);
        ValidationUtil.throwExceptionIfNull(address, "Address");
        this.address = address;
        ValidationUtil.throwExceptionIfNull(phoneNumber, "Phone number");
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
