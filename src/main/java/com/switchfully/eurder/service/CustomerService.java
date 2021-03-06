package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.users.customers.Customer;
import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import com.switchfully.eurder.domain.users.customers.PhoneNumber;
import com.switchfully.eurder.infrastructure.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import com.switchfully.eurder.infrastructure.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer) throws InvalidPhoneNumberException, InvalidEmailException {
        if (validateEmail(customer.getEmailAddress())) {
            throw new InvalidEmailException("The given email is already used by a user!");
        }
        if (validatePhoneNumber(customer.getPhoneNumber())) {
            throw new InvalidPhoneNumberException("The given phone number is already used by user!");
        }
        customerRepository.saveCustomer(customer);
    }

    private boolean validateEmail(String email) {
        return customerRepository.getCustomers().stream()
                .map(Customer::getEmailAddress)
                .anyMatch(e -> e.equals(email));
    }

    private boolean validatePhoneNumber(PhoneNumber phoneNumber) {
        return customerRepository.getCustomers().stream()
                .map(Customer::getPhoneNumber)
                .anyMatch(e -> e.equals(phoneNumber));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getCustomers();
    }

    public Customer getCustomer(UUID id) throws CustomerNotFoundException {
        Customer customer = customerRepository.getCustomerById(id);

        if (ValidationUtil.isNull(customer)) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }


}
