package com.switchfully.eurder.api.orders;

import com.switchfully.eurder.service.OrderService;
import com.switchfully.eurder.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final SecurityService securityService;
    private final OrderService orderService;
    private final OrderMapping orderMapping;

    public OrderController(SecurityService securityService, OrderService orderService, OrderMapping orderMapping) {
        this.securityService = securityService;
        this.orderService = orderService;
        this.orderMapping = orderMapping;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addOrder(@RequestBody CreateOrderDTO createOrderDto,
                             @RequestHeader(value = "Authorization", required = false) String userId)
            throws IllegalAccessException {
        securityService.throwExceptionIfNotCustomer(userId);
        double totalPrice = orderService.createOrder(orderMapping.mapToOrder(createOrderDto), createOrderDto.getCustomerId());
        return orderMapping.mapToOrderDTO(orderMapping.mapToOrder(createOrderDto), totalPrice);
    }
}
