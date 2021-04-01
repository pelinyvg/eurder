package com.switchfully.eurder.domain.orders;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private final Map<UUID, Order> orderRepo;

    public OrderRepository() {
        this.orderRepo = new HashMap<>();
    }

    public Order addNewOrder(Order order) {
        orderRepo.put(order.getId(), order);
        return order;
    }

    public List<Order> getOrdersByCustomer(UUID customerId) {
        return orderRepo.values().stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }
}
