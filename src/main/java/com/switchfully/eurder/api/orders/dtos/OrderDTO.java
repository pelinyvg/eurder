package com.switchfully.eurder.api.orders.dtos;

import com.switchfully.eurder.domain.orders.OrderItem;
import com.switchfully.eurder.domain.users.customers.Customer;

import java.util.List;

public class OrderDTO {
    private final List<OrderItem> orderItems;
    private final Customer customer;
    private final double totalPrice;

    public OrderDTO(List<OrderItem> orderItems, Customer customer, double totalPrice) {
        this.orderItems = orderItems;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
