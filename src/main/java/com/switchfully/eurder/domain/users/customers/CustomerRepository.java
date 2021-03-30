package com.switchfully.eurder.domain.users.customers;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository {
    private final Map<UUID, Customer> customerRepo;

    public CustomerRepository() {
        this.customerRepo = new HashMap<>();
    }

    public void saveCustomer(Customer customer) {
        customerRepo.put(customer.getId(), customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customerRepo.values());
    }
}
