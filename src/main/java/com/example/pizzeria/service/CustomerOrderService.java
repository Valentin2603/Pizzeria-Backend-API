package com.example.pizzeria.service;

import com.example.pizzeria.dto.order.OrderRequest;
import com.example.pizzeria.model.CustomerOrder;

public interface CustomerOrderService extends CrudService<CustomerOrder, Long>{
    CustomerOrder create(OrderRequest request);

    CustomerOrder update(Long id, OrderRequest request);
}
