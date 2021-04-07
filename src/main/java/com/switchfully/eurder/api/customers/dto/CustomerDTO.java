package com.switchfully.eurder.api.customers.dto;

import com.switchfully.eurder.domain.users.customers.Address;
import com.switchfully.eurder.domain.users.customers.PhoneNumber;

import java.util.UUID;

public class CustomerDTO {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final PhoneNumber phoneNumber;

    public CustomerDTO(UUID id, String firstName, String lastName, String emailAddress, Address address, PhoneNumber phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
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
