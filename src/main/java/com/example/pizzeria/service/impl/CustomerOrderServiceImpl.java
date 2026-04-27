package com.example.pizzeria.service.impl;

import com.example.pizzeria.dto.OrderItem.OrderItemRequest;
import com.example.pizzeria.dto.order.OrderRequest;
import com.example.pizzeria.exception.BadRequestException;
import com.example.pizzeria.exception.ResourceNotFoundException;
import com.example.pizzeria.model.Border;
import com.example.pizzeria.model.CustomerOrder;
import com.example.pizzeria.model.OrderItem;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.BorderRepository;
import com.example.pizzeria.repository.CustomerOrderRepository;
import com.example.pizzeria.repository.OrderItemRepository;
import com.example.pizzeria.repository.PizzaRepository;
import com.example.pizzeria.service.CustomerOrderService;
import com.example.pizzeria.service.PricingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.pizzeria.model.enums.CustomerOrderStatus.CREATED;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final PizzaRepository pizzaRepository;
    private final CustomerOrderRepository orderRepository;
    private final BorderRepository borderRepository;
    private final PricingService pricingService;
    private final OrderItemRepository orderItemRepository;

    public CustomerOrderServiceImpl(
            PizzaRepository pizzaRepository,
            CustomerOrderRepository orderRepository,
            BorderRepository borderRepository,
            PricingService pricingService, OrderItemRepository orderItemRepository
    ) {
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        this.borderRepository = borderRepository;
        this.pricingService = pricingService;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public CustomerOrder findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    @Transactional
    @Override
    public CustomerOrder create(OrderRequest request) {
        CustomerOrder order = new CustomerOrder();
        order.setCreatedAt(LocalDateTime.now());
        order.setScheduledTime(request.scheduledTime());
        order.setComment(request.comment());
        order.setStatus(CREATED);

        request.items().forEach(itemRequest -> createOrderItem(order, itemRequest));

        double totalPrice = pricingService.calculateOrderPrice(order.getItems());
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }


    private void createOrderItem(CustomerOrder order, OrderItemRequest requestItem) {
        Pizza pizza = pizzaRepository.findById(requestItem.pizzaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pizza not found: " + requestItem.pizzaId()
                ));

        Border border = borderRepository.findById(requestItem.borderId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Border not found: " + requestItem.borderId()
                ));

        if (!border.isCompatibleWith(pizza)) {
            throw new BadRequestException("Border:" + border.getName() + " is not compatible with pizza: " + pizza.getName());
        }

        OrderItem item = new OrderItem(
                pizza,
                requestItem.size(),
                border,
                requestItem.quantity()
        );

        double itemPrice = pricingService.calculateOrderItemPrice(item);
        item.setPrice(itemPrice);

        order.addItem(item);

    }


    @Transactional
    public CustomerOrder update(Long id, OrderRequest request) {
        CustomerOrder order = orderRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Order not found: " + id)
        );

        order.setScheduledTime(request.scheduledTime());
        order.setComment(request.comment());

        List<OrderItem> oldItems = new ArrayList<>(order.getItems());

        oldItems.forEach(order::removeItem);
        order.getItems().clear();
        request.items().forEach(itemRequest -> createOrderItem(order, itemRequest));
        order.setTotalPrice(pricingService.calculateOrderPrice(order.getItems()));

        return orderRepository.save(order);
    }


    @Override
    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found: " + id);
        }

        orderRepository.deleteById(id);
    }



}
