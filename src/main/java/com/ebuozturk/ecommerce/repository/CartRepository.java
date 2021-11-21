package com.ebuozturk.ecommerce.repository;

import com.ebuozturk.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {

    List<Cart> findByUser_id(String id);
}
