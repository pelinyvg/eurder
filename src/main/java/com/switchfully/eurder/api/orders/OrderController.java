package com.switchfully.eurder.api.orders;

import com.switchfully.eurder.api.orders.dtos.CreateOrderDTO;
import com.switchfully.eurder.api.orders.dtos.ListOrderDTO;
import com.switchfully.eurder.api.orders.dtos.OrderItemShippingTodayDTO;
import com.switchfully.eurder.api.orders.dtos.OrderDTO;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.infrastructure.exceptions.CustomerHasNoOrderException;
import com.switchfully.eurder.infrastructure.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.service.OrderService;
import com.switchfully.eurder.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/today")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItemShippingTodayDTO> getAllItemsShippingToday(@RequestHeader(value = "Authorization", required = false) String userId)
            throws IllegalAccessException {
        securityService.throwExceptionIfNotAdmin(userId);
        return orderService.getItemsShippingToday();
    }

    @GetMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ListOrderDTO getAllOrdersFromACustomer(@PathVariable String customerId,
                                                  @RequestHeader(value = "Authorization", required = false) String userId)
            throws IllegalAccessException, CustomerNotFoundException, CustomerHasNoOrderException {
        securityService.throwExceptionIfNotTheCustomer(customerId, userId);
        List<Order> orderList = orderService.getOrdersByCustomer(customerId);
        return orderMapper.mapToOrderDTOList(orderList, orderService.totalCost(orderList));
    }
}
