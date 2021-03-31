package com.switchfully.eurder.api.orders;

import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderMapping {
    private final CustomerRepository customerRepository;

    public OrderMapping(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CreateOrderDTO mapToCreateOrderDTO(Order order) {
        return new CreateOrderDTO(order.getOrderItems(), order.getCustomer().getId());
    }

    public OrderDTO mapToOrderDTO(Order order, double totalPrice) {
        return new OrderDTO(order.getOrderItems(), order.getCustomer(), totalPrice);
    }

    public Order mapToOrder(CreateOrderDTO createOrderDto) {
        return new Order(customerRepository.getCustomerById(createOrderDto.getCustomerId()), createOrderDto.getOrderItems());
    }
}
