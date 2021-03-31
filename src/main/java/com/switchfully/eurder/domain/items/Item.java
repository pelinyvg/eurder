package com.switchfully.eurder.domain.items;

import com.switchfully.eurder.infrastructure.util.ValidationUtil;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private final Price price;
    private int stock;

    public Item(String name, String description, Price price) {
        this(name, description, price, 0);
    }

    public Item(UUID id, String name, String description, Price price, int stock) {
        this.id = id;
        ValidationUtil.throwExceptionIfNull(name, "Name");
        this.name = name;
        ValidationUtil.throwExceptionIfNull(description, "Description");
        this.description = description;
        ValidationUtil.throwExceptionIfNull(price, "Price");
        this.price = price;
        this.stock = stock;
    }

    public Item(String name, String description, Price price, int stock) {
        this(UUID.randomUUID(), name, description, price, stock);
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }
}
