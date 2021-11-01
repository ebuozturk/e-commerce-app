package com.ebuozturk.ecommerce.repository;


import com.ebuozturk.ecommerce.model.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct,Long> {
   Optional<BasketProduct> findByProduct_idAndBasket_id(Long productId, Long basketId);
   Boolean existsBasketProductById(Long id);
   Boolean existsBasketProductByProduct_IdAndBasket_Id(Long productId,Long basketId);
}
