package com.example.pizzeria.controller;

import com.example.pizzeria.dto.order.OrderRequest;
import com.example.pizzeria.dto.order.OrderResponse;
import com.example.pizzeria.mapper.CustomerOrderMapper;
import com.example.pizzeria.model.CustomerOrder;
import com.example.pizzeria.service.CustomerOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class CustomerOrderController {

    private final CustomerOrderService orderService;
    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderController(CustomerOrderService orderService, CustomerOrderMapper customerOrderMapper) {
        this.orderService = orderService;
        this.customerOrderMapper = customerOrderMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> responses = orderService.findAll().stream()
                .map(customerOrderMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        CustomerOrder order = orderService.findById(id);
        return ResponseEntity.ok(customerOrderMapper.toResponse(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid OrderRequest request) {
        CustomerOrder order = orderService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerOrderMapper.toResponse(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid OrderRequest request
    ) {
        CustomerOrder order = orderService.update(id, request);
        return ResponseEntity.ok(customerOrderMapper.toResponse(order));
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
