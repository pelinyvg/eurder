package com.switchfully.eurder.domain.orders;

import com.switchfully.eurder.domain.users.customers.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepository {
    private Map<UUID, Order> orderRepo;

    public OrderRepository() {
        this.orderRepo = new HashMap<>();
    }

    public Order addNewOrder(Order order) {
        orderRepo.put(order.getId(), order);
        return order;
    }
}
