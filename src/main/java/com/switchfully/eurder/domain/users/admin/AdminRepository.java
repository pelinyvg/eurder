package com.switchfully.eurder.domain.users.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdminRepository {
    private final Map<UUID, Admin> adminRepo;

    public AdminRepository() {
        this.adminRepo = new HashMap<>();
    }
}
