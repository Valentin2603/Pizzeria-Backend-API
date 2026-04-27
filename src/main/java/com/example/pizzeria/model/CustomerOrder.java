package com.example.pizzeria.model;

import com.example.pizzeria.model.enums.CustomerOrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime scheduledTime;

    @Enumerated(value = EnumType.STRING)
    private CustomerOrderStatus status;

    private String comment;

    private double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();


    public CustomerOrder() {

    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
    }



}
