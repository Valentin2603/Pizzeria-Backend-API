package com.example.pizzeria.mapper;

import com.example.pizzeria.dto.OrderItem.OrderItemResponse;
import com.example.pizzeria.dto.order.OrderResponse;
import com.example.pizzeria.model.CustomerOrder;
import com.example.pizzeria.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class CustomerOrderMapper {
    private final PizzaMapper pizzaMapper;
    private final BorderMapper borderMapper;

    public CustomerOrderMapper(PizzaMapper pizzaMapper, BorderMapper borderMapper) {
        this.pizzaMapper = pizzaMapper;
        this.borderMapper = borderMapper;
    }

    public OrderResponse toResponse(CustomerOrder order) {
        return new OrderResponse(
                order.getId(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getScheduledTime(),
                order.getStatus(),
                order.getItems().stream()
                        .map(this::toItemResponse)
                        .toList()
        );
    }

    private OrderItemResponse toItemResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getId(),
                pizzaMapper.toResponse(item.getPizza()),
                borderMapper.toResponse(item.getBorder()),
                item.getSize(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}
