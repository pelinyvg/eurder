package com.switchfully.eurder.api.orders;

import com.switchfully.eurder.domain.orders.OrderItem;
import com.switchfully.eurder.domain.users.customers.Customer;

import java.util.List;

public class OrderDTO {
    private List<OrderItem> orderItems;
    private Customer customer;
    private double totalPrice;

    public OrderDTO(List<OrderItem> orderItems, Customer customer, double totalPrice) {
        this.orderItems = orderItems;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getItemGroups() {
        return orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
