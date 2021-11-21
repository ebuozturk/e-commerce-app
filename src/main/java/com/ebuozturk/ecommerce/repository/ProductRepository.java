package com.ebuozturk.ecommerce.repository;

import com.ebuozturk.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findByCategory_id(String id);
}
