package com.switchfully.eurder.domain.orders;

import com.switchfully.eurder.domain.users.customers.Customer;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<OrderItem> orderItems;
    private Customer customer;

    public Order(Customer customer, List<OrderItem> orderItems) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.id = UUID.randomUUID();
    }

    public List<OrderItem> getItemGroups() {
        return orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public UUID getId() {
        return id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
