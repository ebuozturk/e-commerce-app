package com.ebuozturk.ecommerce.repository;

import com.ebuozturk.ecommerce.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {
    Optional<Basket> findByUser_id(Long id);

}
