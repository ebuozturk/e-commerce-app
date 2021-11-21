package com.ebuozturk.ecommerce.repository;

import com.ebuozturk.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {

    List<Order> findByUser_id(String userId);

}
