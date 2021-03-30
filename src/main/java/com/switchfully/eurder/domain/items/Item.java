package com.switchfully.eurder.domain.items;

public class Item {
    private final String name;
    private final String description;
    private final Price price;
    private final double amount;

    public Item(String name, String description, Price price, double amount) {
        this.name = name;
        this.description = description;
        this.price = price;
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
}
