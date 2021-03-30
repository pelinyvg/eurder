package com.switchfully.eurder.domain.users.admin;

import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdminRepository {
    private final Map<UUID, Admin> adminRepo;

    public AdminRepository() throws InvalidEmailException {
        this.adminRepo = new HashMap<>();
        Admin admin = new Admin(UUID.fromString("256f5896-t8td-5ds8-859674585693"), "Admin", "Root", "admin@root.com");
        adminRepo.put(admin.getId(), admin);
    }

    public boolean isAdmin(UUID id) {
        return adminRepo.get(id) != null;
    }
}
