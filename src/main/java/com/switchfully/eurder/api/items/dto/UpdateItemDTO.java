package com.switchfully.eurder.api.items.dto;

import com.switchfully.eurder.domain.items.Price;

public class UpdateItemDTO {
    private final String name;
    private final String description;
    private final Price price;
    private final int stock;

    public UpdateItemDTO(String name, String description, Price price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
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

    @Override
    public String toString() {
        return "UpdateItemDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
