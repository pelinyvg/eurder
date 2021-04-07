package com.switchfully.eurder.api.customers;

import com.switchfully.eurder.api.customers.dto.CreateCustomerDTO;
import com.switchfully.eurder.api.customers.dto.CustomerDTO;
import com.switchfully.eurder.api.orders.dtos.ListOrderDTO;
import com.switchfully.eurder.api.orders.OrderMapper;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.infrastructure.exceptions.CustomerHasNoOrderException;
import com.switchfully.eurder.infrastructure.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import com.switchfully.eurder.service.CustomerService;
import com.switchfully.eurder.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final CustomerMapper mapper;
    private final SecurityService securityService;


    public CustomerController(CustomerService customerService, CustomerMapper mapper, SecurityService securityService) {
        this.customerService = customerService;
        this.mapper = mapper;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) throws InvalidEmailException, InvalidPhoneNumberException {
        customerService.createCustomer(mapper.mapCreateCustomerDTOToCustomer(createCustomerDTO));
        logger.info("New customer created: " + createCustomerDTO.toString());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers(@RequestHeader(value = "Authorization", required = false) String userId) throws IllegalAccessException {
        securityService.throwExceptionIfNotAdmin(userId);
        return mapper.mapToDTOList(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomer(@PathVariable UUID id, @RequestHeader(value = "Authorization", required = false) String userId) throws IllegalAccessException, CustomerNotFoundException {
        securityService.throwExceptionIfNotAdmin(userId);
        return mapper.mapToDTO(customerService.getCustomer(id));
    }
}
