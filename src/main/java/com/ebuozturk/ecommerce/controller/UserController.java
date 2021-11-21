package com.ebuozturk.ecommerce.controller;


import com.ebuozturk.ecommerce.dto.address.AddressDto;
import com.ebuozturk.ecommerce.dto.cart.CartDto;
import com.ebuozturk.ecommerce.dto.user.CreateUserRequest;
import com.ebuozturk.ecommerce.dto.user.UpdateUserRequest;
import com.ebuozturk.ecommerce.dto.user.UserDto;
import com.ebuozturk.ecommerce.service.AddressService;
import com.ebuozturk.ecommerce.service.CartService;
import com.ebuozturk.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;
    private final CartService cartService;

    public UserController(UserService userService, AddressService addressService, CartService cartService) {
        this.userService = userService;
        this.addressService = addressService;
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserDto> getUserByMail(@PathVariable("mail") String mail){
        return ResponseEntity.ok(userService.getUserByMail(mail));
    }
    @GetMapping("/{id}/id")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("{id}/address")
    public ResponseEntity<List<AddressDto>> getUsersAddresses(@PathVariable("id") String id){

        return ResponseEntity.ok(addressService.getAddressesByUserId(id));
    }
    @GetMapping("{id}/cart")
    public ResponseEntity<List<CartDto>> getUsersCarts(@PathVariable("id") String id){
        return ResponseEntity.ok(cartService.getCartsByUserId(id));
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest){
        return  ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserRequest request){

        return ResponseEntity.ok(userService.updateUser(id,request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") String id){
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable("id") String id){
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id){

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
