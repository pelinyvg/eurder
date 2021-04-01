package com.switchfully.eurder.api.orders;

import java.util.List;
import java.util.UUID;

public class ListOrderDTO {
    private final List<ReportOrderDTO> orderItems;
    private final UUID customerId;
    private final double totalPrice;

    public ListOrderDTO(List<ReportOrderDTO> orderItems, UUID customerId, double totalPrice) {
        this.orderItems = orderItems;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }

    public List<ReportOrderDTO> getOrderItems() {
        return orderItems;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
