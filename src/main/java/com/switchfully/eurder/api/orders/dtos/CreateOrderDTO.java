package com.switchfully.eurder.api.orders.dtos;

import com.switchfully.eurder.domain.orders.OrderItem;

import java.util.List;
import java.util.UUID;

public class CreateOrderDTO {
    private final List<OrderItem> orderItems;
    private final UUID customerId;

    public CreateOrderDTO(List<OrderItem> orderItems, UUID customerId) {
        this.orderItems = orderItems;
        this.customerId = customerId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
