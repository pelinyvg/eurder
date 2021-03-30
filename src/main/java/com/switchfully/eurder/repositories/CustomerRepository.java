package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CustomerRepository {
    private final Map<UUID, Customer> customerRepo;

    public CustomerRepository() {
        this.customerRepo = new HashMap<>();
    }

}
