package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.users.admin.AdminRepository;
import com.switchfully.eurder.infrastructure.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {
    private static final String ADMIN_WARNING = "Access Denied: Only admins can access this page!";
    private final AdminRepository adminRepository;

    public SecurityService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void throwExceptionIfNotAdmin(String userId) throws IllegalAccessException {
        if (ValidationUtil.isNull(userId) || !ValidationUtil.isUUIDValid(userId) || !isAdmin(userId)) {
            throw new IllegalAccessException(ADMIN_WARNING);
        }
    }

    private boolean isAdmin(String userId) {
        return adminRepository.isAdmin(UUID.fromString(userId));
    }
}
