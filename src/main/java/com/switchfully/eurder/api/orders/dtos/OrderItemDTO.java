package com.switchfully.eurder.api.orders.dtos;

public class OrderItemDTO {
    private final String name;
    private final int amount;

    public OrderItemDTO(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
