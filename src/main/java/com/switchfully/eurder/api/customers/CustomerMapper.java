package com.switchfully.eurder.api.customers;

import com.switchfully.eurder.domain.users.customers.Customer;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer mapCreateCustomerDTOToCustomer(CreateCustomerDTO createCustomerDTO) throws InvalidEmailException, InvalidPhoneNumberException {
        return new Customer(createCustomerDTO.getFirstName(), createCustomerDTO.getLastName(), createCustomerDTO.getEmailAddress(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
    }
}
