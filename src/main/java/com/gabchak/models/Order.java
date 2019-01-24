package com.gabchak.models;

import com.gabchak.controllers.external.model.OrderDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private User customer;
    private LocalDate orderDate;
    private String orderComment;
    private Double orderAmount;
    private Map<Product, Integer> products;

    public void addProduct(Product product, Integer quantity) {
        products.put(product, quantity);
    }

    public void increaseQuantity(Product product, Integer quantity) {
        products.merge(product, quantity, (a, b) -> a + b);
    }

    public static Order of(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setCustomer(orderDto.getCustomer());
        order.setOrderDate(orderDto.getOrderDate());
        order.setOrderAmount(orderDto.getOrderAmount());
        order.setOrderComment(orderDto.getOrderComment());
        order.setProducts(orderDto.getProducts());
        return order;
    }
}
