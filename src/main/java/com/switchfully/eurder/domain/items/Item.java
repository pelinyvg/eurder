package com.switchfully.eurder.domain.items;

import com.switchfully.eurder.infrastructure.util.ValidationUtil;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private final Price price;
    private final double amount;

    public Item(String name, String description, Price price, double amount) {
        this.id = UUID.randomUUID();
        ValidationUtil.throwExceptionIfNull(name, "Name");
        this.name = name;
        ValidationUtil.throwExceptionIfNull(description, "Description");
        this.description = description;
        ValidationUtil.throwExceptionIfNull(price, "Price");
        this.price = price;
        ValidationUtil.throwExceptionIfNull(amount, "Amount");
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    public UUID getId() {
        return id;
    }
}
