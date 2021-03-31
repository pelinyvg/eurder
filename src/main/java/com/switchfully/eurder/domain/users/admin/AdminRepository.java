package com.switchfully.eurder.domain.users.admin;

import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class AdminRepository {
    private final Map<UUID, Admin> adminRepo;

    public AdminRepository() throws InvalidEmailException {
        this.adminRepo = new HashMap<>();
        Admin admin = new Admin(UUID.fromString("256f5896-e88d-56d8-8e96-745856936805"), "Admin", "Root", "admin@root.com");
        adminRepo.put(admin.getId(), admin);
    }

    public boolean isAdmin(UUID id) {
        return adminRepo.get(id) != null;
    }
}
