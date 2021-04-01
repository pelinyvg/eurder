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
    private final OrderMapper orderMapper;

    public OrderController(SecurityService securityService, OrderService orderService, OrderMapper orderMapper) {
        this.securityService = securityService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addOrder(@RequestBody CreateOrderDTO createOrderDto,
                             @RequestHeader(value = "Authorization", required = false) String userId)
            throws IllegalAccessException {
        securityService.throwExceptionIfNotCustomer(userId);
        double totalPrice = orderService.createOrder(orderMapper.mapToOrder(createOrderDto), createOrderDto.getCustomerId());
        return orderMapper.mapToOrderDTO(orderMapper.mapToOrder(createOrderDto), totalPrice);
    }
}
