package com.switchfully.eurder.api.customers;

import com.switchfully.eurder.domain.users.customers.Address;
import com.switchfully.eurder.domain.users.customers.PhoneNumber;

public class CreateCustomerDTO {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final PhoneNumber phoneNumber;

    public CreateCustomerDTO(String firstName, String lastName, String emailAddress, Address address, PhoneNumber phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
