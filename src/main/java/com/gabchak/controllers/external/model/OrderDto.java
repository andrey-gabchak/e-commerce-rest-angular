package com.gabchak.controllers.external.model;

import com.gabchak.models.Order;
import com.gabchak.models.Product;
import com.gabchak.models.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class OrderDto {

    private Long orderId;
    private User customer;
    private LocalDate orderDate;
    private String orderComment;
    private Double orderAmount;
    private Map<Product, Integer> products;


    public void increaseQuantity(Product product, Integer quantity) {
        products.merge(product, quantity, (a, b) -> a + b);
    }

    public static OrderDto of(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setCustomer(order.getCustomer());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderAmount(order.getOrderAmount());
        orderDto.setOrderComment(order.getOrderComment());
        orderDto.setProducts(order.getProducts());
        return orderDto;
    }
}
