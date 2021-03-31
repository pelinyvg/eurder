package com.switchfully.eurder.domain.users.customers;

import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository {
    private final Map<UUID, Customer> customerRepo;

    public CustomerRepository() throws InvalidEmailException, InvalidPhoneNumberException {
        this.customerRepo = new HashMap<>();
        Address address = new Address("Street", "30A", "City", "BE3082");
        Customer customer = new Customer(UUID.fromString("256f5796-e63d-56d8-8e99-745850936805"), "firstname", "lastname", "emai@address.com", address, new PhoneNumber(32, "12345851"));
        customerRepo.put(customer.getId(), customer);
    }

    public void saveCustomer(Customer customer) {
        customerRepo.put(customer.getId(), customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customerRepo.values());
    }

    public boolean isCustomer(UUID id) {
        return customerRepo.get(id) != null;
    }

    public Customer getCustomerById(UUID customerId) {
        return customerRepo.get(customerId);
    }
}
