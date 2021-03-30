package com.switchfully.eurder.domain.users.admin;

import com.switchfully.eurder.domain.users.User;
import com.switchfully.eurder.domain.users.UserRole;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;

import java.util.UUID;

public class Admin extends User {
    public Admin(UUID id, String firstName, String lastName, String emailAddress)
            throws InvalidEmailException {
        super(id, firstName, lastName, emailAddress, UserRole.ADMIN);
    }
}
