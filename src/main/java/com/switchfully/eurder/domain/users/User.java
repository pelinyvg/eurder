package com.switchfully.eurder.domain.users;

import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.util.ValidationUtil;

import java.util.UUID;

public abstract class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final UserRole role;

    public User(String firstName, String lastName, String emailAddress, UserRole role) throws InvalidEmailException {
        this(UUID.randomUUID(), firstName, lastName, emailAddress, role);
    }

    public User(UUID id, String firstName, String lastName, String emailAddress, UserRole role) throws InvalidEmailException {
        this.id = id;
        ValidationUtil.throwExceptionIfNull(firstName, "Firstname");
        this.firstName = firstName;
        ValidationUtil.throwExceptionIfNull(lastName, "Lastname");
        this.lastName = lastName;
        ValidationUtil.throwExceptionIfNull(emailAddress, "Email address");
        if (ValidationUtil.isEmailValid(emailAddress)) {
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidEmailException("The given email is not in the correct format");
        }
        this.role = role;
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

    public UserRole getRole() {
        return role;
    }
}
