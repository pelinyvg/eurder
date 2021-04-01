package com.switchfully.eurder.api.orders;

import java.util.List;
import java.util.UUID;

public class ReportOrderDTO {
    private final UUID id;
    private final List<OrderItemDTO> orderItems;

    public ReportOrderDTO(UUID id, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public UUID getId() {
        return id;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }
}
