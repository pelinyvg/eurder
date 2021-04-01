package com.switchfully.eurder.api.orders;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.domain.orders.OrderItem;
import com.switchfully.eurder.domain.users.customers.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public OrderMapper(CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public OrderDTO mapToOrderDTO(Order order, double totalPrice) {
        return new OrderDTO(order.getOrderItems(), order.getCustomer(), totalPrice);
    }

    public ReportOrderDTO mapToReportOrderDTO(Order order) {
        return new ReportOrderDTO(order.getId(), order.getOrderItems().stream().map(this::mapOrderItemDTO).collect(Collectors.toList()));
    }

    public OrderItemDTO mapOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(itemRepository.getItemById(orderItem.getItemId()).getName(), orderItem.getAmount());
    }


    public Order mapToOrder(CreateOrderDTO createOrderDto) {
        return new Order(customerRepository.getCustomerById(createOrderDto.getCustomerId()), createOrderDto.getOrderItems());
    }

    public ListOrderDTO mapToOrderDTOList(List<Order> orders, double totalPrice) {
        return new ListOrderDTO(orders.stream().map(this::mapToReportOrderDTO).collect(Collectors.toList()), orders.get(0).getCustomer().getId(), totalPrice);
    }
}
