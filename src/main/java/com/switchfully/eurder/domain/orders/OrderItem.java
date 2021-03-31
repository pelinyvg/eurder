package com.switchfully.eurder.domain.orders;

import java.time.LocalDate;
import java.util.UUID;

public class OrderItem {
    private final UUID itemId;
    private final int amount;
    private LocalDate shippingDate;

    public OrderItem(UUID itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }
}
