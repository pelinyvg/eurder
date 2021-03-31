package com.switchfully.eurder.api.customers;

import com.switchfully.eurder.domain.users.customers.Customer;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public Customer mapCreateCustomerDTOToCustomer(CreateCustomerDTO createCustomerDTO) throws InvalidEmailException, InvalidPhoneNumberException {
        return new Customer(createCustomerDTO.getFirstName(), createCustomerDTO.getLastName(), createCustomerDTO.getEmailAddress(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
    }

    public List<CustomerDTO> mapToDTOList(List<Customer> allCustomers) {
        return allCustomers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CustomerDTO mapToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmailAddress(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }
}
