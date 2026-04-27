package com.example.pizzeria.dto.order;

import com.example.pizzeria.dto.OrderItem.OrderItemRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest (
        @NotEmpty
        @Valid
        List<OrderItemRequest> items,
        @Future
        @NotNull
        LocalDateTime scheduledTime,
        String comment
){
}
