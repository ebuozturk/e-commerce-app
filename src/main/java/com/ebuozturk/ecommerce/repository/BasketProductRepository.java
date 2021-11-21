package com.ebuozturk.ecommerce.repository;


import com.ebuozturk.ecommerce.model.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct,String> {
   Optional<BasketProduct> findByProduct_idAndBasket_id(String productId, String basketId);
   Boolean existsBasketProductById(String id);
   Boolean existsBasketProductByProduct_IdAndBasket_Id(String productId,String basketId);
}
