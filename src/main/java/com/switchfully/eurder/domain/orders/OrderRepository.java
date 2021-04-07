package com.switchfully.eurder.domain.orders;

import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private final Map<UUID, Order> orderRepo;
    private final CustomerRepository customerRepository;

    public OrderRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.orderRepo = new HashMap<>();
        Order order = new Order(UUID.fromString("256f5799-e63d-51d8-8e99-705120936805"), this.customerRepository.getCustomerById(UUID.fromString("256f5796-e63d-56d8-8e99-745850936805")), List.of(new OrderItem(UUID.fromString("256f5696-e93d-56e8-8d90-745850123805"), 1)));
        orderRepo.put(order.getId(), order);
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

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderRepo.values());
    }

    public Order getOrderById(String orderId) {
        return orderRepo.get(UUID.fromString(orderId));
    }
}
