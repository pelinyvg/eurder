package com.switchfully.eurder.api.customers;

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
    private final CustomerService service;
    private final CustomerMapper mapper;
    private final SecurityService securityService;

    public CustomerController(CustomerService service, CustomerMapper mapper, SecurityService securityService) {
        this.service = service;
        this.mapper = mapper;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) throws InvalidEmailException, InvalidPhoneNumberException {
        service.createCustomer(mapper.mapCreateCustomerDTOToCustomer(createCustomerDTO));
        logger.info("New customer created: " + createCustomerDTO.toString());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers(@RequestHeader(value = "Authorization", required = false) String userId) throws IllegalAccessException {
        securityService.throwExceptionIfNotAdmin(userId);
        return mapper.mapToDTOList(service.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomer(@PathVariable UUID id, @RequestHeader(value = "Authorization", required = false) String userId) throws IllegalAccessException {
        securityService.throwExceptionIfNotAdmin(userId);
        return mapper.mapToDTO(service.getCustomer(id));
    }
}
