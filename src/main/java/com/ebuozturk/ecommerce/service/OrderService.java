package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.OrderConverter;
import com.ebuozturk.ecommerce.dto.order.OrderDto;
import com.ebuozturk.ecommerce.dto.orderitem.OrderItemDto;
import com.ebuozturk.ecommerce.model.*;
import com.ebuozturk.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final BasketService basketService;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final OrderConverter converter;

    public OrderService(OrderRepository repository, BasketService basketService, UserService userService, OrderItemService orderItemService, OrderConverter converter) {
        this.repository = repository;
        this.basketService = basketService;
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.converter = converter;
    }


    public List<OrderDto> placeOrder(Long userId){
        User user = userService.findById(userId);

        Basket basket = basketService.findByUserId(userId);

        Order newOrder = new Order(
                LocalDateTime.now(),
                basketService.calculateTotalPrice(basket),
                user
                );

        Set<OrderItem> orderItems = basket.getProducts().stream().map(product -> {
            OrderItem orderItem = new OrderItem(
                    product.getQuantity(),
                    product.getProduct().getUnitPrice(),
                    LocalDateTime.now(),
                    newOrder,
                    product.getProduct()
            );
            newOrder.getOrderItems().add(orderItem);
            basketService.removeProductFromBasket(userId,product.getId());
            return orderItem;

        }).collect(Collectors.toSet());

        orderItems.forEach(orderItemService::saveOrderItem);
        return converter.convert(findByUserId(userId));
    }

    public OrderDto updateStatus(Long orderId, Status status){
        Order order = findById(orderId);
        Order updateOrder =  new Order(order.getId(),
                order.getCreatedDate(),
                order.getTotalPrice(),
                order.getOrderItems(),
                order.getUser(),
                status);
        return converter.convert(repository.save(updateOrder));
    }

    protected List<Order> findByUserId(Long userId){
        return repository.findByUser_id(userId);
    }

    protected Order findById(Long id){
        return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Bulamadık"));
    }
}
