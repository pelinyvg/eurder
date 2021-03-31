package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.users.admin.AdminRepository;
import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import com.switchfully.eurder.infrastructure.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {
    private static final String ADMIN_WARNING = "Access Denied: Only admins can access this page!";
    private static final String CUSTOMER_WARNING = "Access Denied: Only customers can place an order!";
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    public SecurityService(AdminRepository adminRepository, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
    }

    public void throwExceptionIfNotAdmin(String userId) throws IllegalAccessException {
        if (validationId(userId) || !isAdmin(userId)) {
            throw new IllegalAccessException(ADMIN_WARNING);
        }
    }

    public void throwExceptionIfNotCustomer(String userId) throws IllegalAccessException {
        if (validationId(userId) || !isCustomer(userId)) {
            throw new IllegalAccessException(CUSTOMER_WARNING);
        }
    }

    private boolean validationId(String userId) {
        return ValidationUtil.isNull(userId) || !ValidationUtil.isUUIDValid(userId);
    }

    private boolean isAdmin(String userId) {
        return adminRepository.isAdmin(UUID.fromString(userId));
    }

    private boolean isCustomer(String userId) {
        return customerRepository.isCustomer(UUID.fromString(userId));
    }
}
