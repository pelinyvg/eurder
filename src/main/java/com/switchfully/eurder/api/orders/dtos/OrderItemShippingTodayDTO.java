package com.switchfully.eurder.api.orders.dtos;

import com.switchfully.eurder.domain.users.customers.Address;

import java.time.LocalDate;

public class OrderItemShippingTodayDTO {
    private final String name;
    private final int amount;
    private final LocalDate shippingDate;
    private final Address customerAddress;
    private final double price;

    public OrderItemShippingTodayDTO(String name, int amount, LocalDate shippingDate, Address customerAddress, double price) {
        this.name = name;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.customerAddress = customerAddress;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public double getPrice() {
        return price;
    }
}
