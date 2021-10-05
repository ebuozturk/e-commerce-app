package com.ebuozturk.ecommerce.repository;

import com.ebuozturk.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findAddressByUser_id(Long id);
}
