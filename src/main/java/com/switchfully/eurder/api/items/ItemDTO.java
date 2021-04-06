package com.switchfully.eurder.api.items;

import com.switchfully.eurder.domain.items.Price;
import com.switchfully.eurder.domain.items.StockAvailability;

public class ItemDTO {
    private final String id;
    private final String name;
    private final String description;
    private final Price price;
    private final int stock;
    private final StockAvailability stockAvailability;

    public ItemDTO(String id, String name, String description, Price price, int stock, StockAvailability stockAvailability) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockAvailability = stockAvailability;
    }

    public String getId() {
        return id;
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

    public StockAvailability getStockAvailability() {
        return stockAvailability;
    }
}
