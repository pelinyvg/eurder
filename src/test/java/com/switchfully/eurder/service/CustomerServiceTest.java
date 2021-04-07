package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.users.customers.Address;
import com.switchfully.eurder.domain.users.customers.Customer;
import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import com.switchfully.eurder.domain.users.customers.PhoneNumber;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    CustomerRepository repository = new CustomerRepository();
    CustomerService service = new CustomerService(repository);
    Customer customer;
    Address address = new Address("Street", "30A", "City", "BE3082");
    PhoneNumber phoneNumber = new PhoneNumber(32, "1236458846");

    CustomerServiceTest() throws InvalidEmailException, InvalidPhoneNumberException {
    }

    @Test
    void shouldCreateCustomer() throws InvalidPhoneNumberException, InvalidEmailException {
        customer = new Customer("firstname", "lastname", "email@address.com", address, phoneNumber);
        try {
            service.createCustomer(customer);
        } catch (InvalidPhoneNumberException e) {
            e.printStackTrace();
        }
        assertEquals(customer, repository.getCustomers().get(0));
    }

    @Test
    void shouldThrowException_givenInvalidEmailDuplicate() throws InvalidPhoneNumberException, InvalidEmailException {
        customer = new Customer("firstname", "lastname", "email@address.com", address, phoneNumber);
        service.createCustomer(customer);

        Customer customer2 = new Customer("firstname", "lastname", "email@address.com", address, new PhoneNumber(32, "123456851"));
        Executable executable = () -> service.createCustomer(customer2);
        assertThrows(InvalidEmailException.class, executable);
    }

    @Test
    void shouldThrowException_givenInvalidEmailFormat() {
        Executable executable = () -> new Customer("firstname", "lastname", "emailAddress.com", address, phoneNumber);
        assertThrows(InvalidEmailException.class, executable);
    }

    @Test
    void shouldThrowException_givenInvalidPhoneNumberDuplicate() throws InvalidPhoneNumberException, InvalidEmailException {
        customer = new Customer("firstname", "lastname", "email@address.com", address, phoneNumber);

        service.createCustomer(customer);

        Customer customer2 = new Customer("firstname", "lastname", "email@address.com", address, new PhoneNumber(32, "1236458846"));
        Executable executable = () -> service.createCustomer(customer2);
        assertThrows(InvalidEmailException.class, executable);
    }

    @Test
    void shouldThrowException_givenInvalidPhoneFormat() {
        Executable executable = () -> new Customer("firstname", "lastname", "emailAddress.com", address, new PhoneNumber(32, "4562dgf6"));
        assertThrows(InvalidEmailException.class, executable);
    }

    @Test
    void shouldThrowException_givenNullPhoneNumber() {
        Executable executable = () -> customer = new Customer("firstname", "lastname", "email@address.com", address, null);
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void shouldThrowException_givenNullLastName() {
        Executable executable = () -> customer = new Customer("firstname", null, "email@address.com", address, phoneNumber);
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void shouldThrowException_givenNullFirstName() {
        Executable executable = () -> customer = new Customer(null, "lastname", "email@address.com", address, phoneNumber);
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void shouldThrowException_givenNullAddress() {
        Executable executable = () -> customer = new Customer("firstname", "lastname", "email@address.com", null, phoneNumber);
        assertThrows(IllegalArgumentException.class, executable);
    }
}
