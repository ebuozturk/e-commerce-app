package com.ebuozturk.ecommerce.converter;

import com.ebuozturk.ecommerce.dto.order.OrderDto;
import com.ebuozturk.ecommerce.dto.orderitem.OrderItemDto;
import com.ebuozturk.ecommerce.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    private final ProductConverter productConverter;
    private final AddressConverter addressConverter;
    public OrderConverter(ProductConverter productConverter, AddressConverter addressConverter) {
        this.productConverter = productConverter;
        this.addressConverter = addressConverter;
    }

    public OrderDto convert(Order order){
        return new OrderDto(
                order.getId(),
                order.getCreatedDate(),
                order.getTotalPrice(),
                order.getOrderItems()
                        .stream().map(orderItem -> {
                    return new OrderItemDto(
                            orderItem.getId(),
                            orderItem.getCreatedDate(),
                            orderItem.getQuantity(),
                            productConverter.convert(orderItem.getProduct())
                    );
                }).collect(Collectors.toList()),
                addressConverter.convert(order.getOrderAddress()),
                order.getStatus().toString()
        );
    }
    public List<OrderDto> convert(List<Order> orderList){
        return orderList.stream().map(this::convert).collect(Collectors.toList());
    }
}
