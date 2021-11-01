package com.ebuozturk.ecommerce.repository;

import com.ebuozturk.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser_id(Long userId);
}
