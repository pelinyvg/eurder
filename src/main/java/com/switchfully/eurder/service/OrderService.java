package com.switchfully.eurder.service;

import com.switchfully.eurder.api.orders.dtos.OrderItemShippingTodayDTO;
import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.domain.items.Price;
import com.switchfully.eurder.domain.orders.OrderItem;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.domain.orders.OrderRepository;
import com.switchfully.eurder.domain.users.customers.Customer;
import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import com.switchfully.eurder.infrastructure.exceptions.CustomerHasNoOrderException;
import com.switchfully.eurder.infrastructure.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.infrastructure.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
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
        orderToCreate.getOrderItems().forEach(this::setShippingDate);
        setStockForEachItemAfterOrdering(orderToCreate);
        double totalCost = calculateTotalPrice(order);
        orderToCreate.setTotalPrice(totalCost);
        return totalCost;
    }

    public List<Order> getOrdersByCustomer(String id) throws CustomerNotFoundException, CustomerHasNoOrderException {
        if (!ValidationUtil.isUUIDValid(id) || customerRepository.getCustomerById(UUID.fromString(id)) == null) {
            throw new CustomerNotFoundException();
        }
        List<Order> orders = orderRepository.getOrdersByCustomer(UUID.fromString(id));
        totalCost(orders);
        if (orders.size() > 0) {
            return orders;
        } else {
            throw new CustomerHasNoOrderException();
        }
    }

    public double totalCost(List<Order> orders) {
        return orders.stream().map(Order::getTotalPrice).reduce(Double::sum).orElse(0.0);
    }

    public List<OrderItemShippingTodayDTO> getItemsShippingToday() {
        return orderRepository.getAllOrders().stream()
                .map(this::getItemsShippingTodayFromAnOrder)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private void setStockForEachItemAfterOrdering(Order orderToCreate) {
        for (OrderItem orderItem : orderToCreate.getOrderItems()) {
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
        double cost = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            cost += (orderItem.getAmount() * itemRepository.getItemById(orderItem.getItemId()).getPrice().getPriceNumber());
        }
        return cost;
    }

    private List<OrderItemShippingTodayDTO> getItemsShippingTodayFromAnOrder(Order order) {
        return order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getShippingDate().isEqual(LocalDate.now()))
                .map(orderItem -> new OrderItemShippingTodayDTO(
                        itemRepository.getItemById(orderItem.getItemId()).getName(),
                        orderItem.getAmount(),
                        orderItem.getShippingDate(),
                        order.getCustomer().getAddress(),
                        calculateTotalPrice(order)))
                .collect(Collectors.toList());
    }

    public Order getOrderById(String orderId) {
        if (ValidationUtil.isUUIDValid(orderId)) {
            logger.warn("Order repository is being reached. Can throw exception if the id: " + orderId + " does not exist in repository!");
            Optional<Order> order = Optional.ofNullable(orderRepository.getOrderById(orderId));
            return order.orElseThrow(IllegalArgumentException::new);
        }
        throw new IllegalArgumentException("Order id is not valid!");
    }
}
