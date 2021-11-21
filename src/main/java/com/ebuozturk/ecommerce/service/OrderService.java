package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.OrderConverter;
import com.ebuozturk.ecommerce.dto.order.OrderDto;
import com.ebuozturk.ecommerce.exception.EmptyBasketException;
import com.ebuozturk.ecommerce.exception.OrderNotFoundException;
import com.ebuozturk.ecommerce.model.*;
import com.ebuozturk.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final BasketService basketService;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final OrderConverter converter;
    private final AddressService addressService;

    public OrderService(OrderRepository repository, BasketService basketService, UserService userService, OrderItemService orderItemService, OrderConverter converter, AddressService addressService) {
        this.repository = repository;
        this.basketService = basketService;
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.converter = converter;
        this.addressService = addressService;
    }


    public OrderDto getOrderById(String id){
        return converter.convert(findById(id));
    }

    public List<OrderDto> getAllByUserId(String id) {
        return converter.convert(findByUserId(id));
    }

    public OrderDto placeOrder(String userId,String addressId){
        User user = userService.findById(userId);
        Address address = addressService.findById(addressId);
        Basket basket = basketService.findByUserId(userId);

        if(basket.getProducts().size()>0){
            Order newOrder = new Order(
                    LocalDateTime.now(),
                    basketService.calculateTotalPrice(basket),
                    address,
                    user
            );

            basket.getProducts().stream().forEach(basketProduct -> {

                OrderItem orderItem = new OrderItem(
                        basketProduct.getQuantity(),
                        basketProduct.getProduct().getUnitPrice(),
                        LocalDateTime.now(),
                        newOrder,
                        basketProduct.getProduct()
                );

                newOrder.getOrderItems().add(orderItem);
                basketService.removeProductFromBasket(userId,basketProduct.getProduct().getId());

            });
            return converter.convert(repository.save(newOrder));
        }
        else{
            throw new EmptyBasketException("There is no product in basket!");
        }

    }

    public OrderDto updateStatus(String orderId, Status status){
        Order order = findById(orderId);
        Order updateOrder =  new Order(order.getId(),
                order.getCreatedDate(),
                order.getTotalPrice(),
                order.getOrderAddress(),
                order.getOrderItems(),
                order.getUser(),
                status);
        return converter.convert(repository.save(updateOrder));
    }

    public void deleteOrderById(String id) {
        Order  order = findById(id);
        repository.delete(order);
    }
    protected List<Order> findByUserId(String userId){
        return repository.findByUser_id(userId);
    }

    protected Order findById(String id){
        return repository.findById(id).orElseThrow(()-> new OrderNotFoundException("The order couldn't be found by following id: "+id));
    }

}
