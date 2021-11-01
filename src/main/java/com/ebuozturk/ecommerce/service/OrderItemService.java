package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.model.OrderItem;
import com.ebuozturk.ecommerce.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public void saveOrderItem(OrderItem orderItem){
        repository.save(orderItem);
    }
}
