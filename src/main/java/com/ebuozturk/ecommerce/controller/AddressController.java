package com.ebuozturk.ecommerce.controller;

import com.ebuozturk.ecommerce.dto.AddressDto;
import com.ebuozturk.ecommerce.dto.CreateAddressRequest;
import com.ebuozturk.ecommerce.dto.UpdateAddressRequest;
import com.ebuozturk.ecommerce.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses(){
        return ResponseEntity.ok(service.getAllAddresses());
    }
    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody CreateAddressRequest request){
        return ResponseEntity.ok(service.createAddress(request));
    }

    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(@RequestBody UpdateAddressRequest request){
        return ResponseEntity.ok(service.updateAddress(request));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id){
        service.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
