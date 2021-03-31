package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.domain.items.Price;
import com.switchfully.eurder.domain.orders.OrderItem;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.domain.orders.OrderRepository;
import com.switchfully.eurder.domain.users.customers.Customer;
import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public double createOrder(Order order, UUID customerId) {
        Order orderToCreate = orderRepository.addNewOrder(order);
        Customer customer = customerRepository.getCustomerById(customerId);
        orderToCreate.setCustomer(customer);
        orderToCreate.getItemGroups().forEach(this::setShippingDate);
        setStockForEachItemAfterOrdering(orderToCreate);
        return calculateTotalPrice(order);
    }

    private void setStockForEachItemAfterOrdering(Order orderToCreate) {
        for (OrderItem orderItem : orderToCreate.getItemGroups()) {
            Item item = itemRepository.getItemById(orderItem.getItemId());
            item.setStock(item.getStock() - orderItem.getAmount());
        }
    }

    private void setShippingDate(OrderItem orderItem) {
        if (itemRepository.getItemById(orderItem.getItemId()).getStock() > 0) {
            orderItem.setShippingDate(LocalDate.now().plusDays(1));
        } else {
            orderItem.setShippingDate(LocalDate.now().plusDays(7));
        }
    }

    private double calculateTotalPrice(Order order) {
        return order.getItemGroups().stream()
                .map(orderItem -> itemRepository.getItemById(orderItem.getItemId()))
                .map(Item::getPrice)
                .map(Price::getPriceNumber)
                .reduce(Double::sum).orElseThrow();
    }
}
