package com.switchfully.eurder.infrastructure.security.external;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class FakeAuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final List<ExternalAuthentication> externalAuthentications;

    public FakeAuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.externalAuthentications = defaultUsers();
    }

    private ArrayList<ExternalAuthentication> defaultUsers() {
        return newArrayList(
                ExternalAuthentication.externalAuthentication().withUsername("CRIMI").withPassword("NAL").withRoles(newArrayList("ADMIN")),
                ExternalAuthentication.externalAuthentication().withUsername("MOB").withPassword("BOSS").withRoles(newArrayList("CUSTOMER")));
    }

    public ExternalAuthentication getUser(String username, String password) {
        return externalAuthentications.stream()
                .filter(externalAuthentication -> externalAuthentication.getUsername().equals(username))
                .filter(externalAuthentication -> passwordEncoder.matches(password, externalAuthentication.getPassword()))
                .findFirst()
                .orElse(null);
    }
}
