package com.gabchak.controllers.external.model;

import com.gabchak.models.Order;
import com.gabchak.models.Product;
import com.gabchak.models.User;

import java.time.LocalDate;
import java.util.Map;

public class OrderDto {

    private Long orderId;
    private User customer;
    private LocalDate orderDate;
    private String orderComment;
    private Double orderAmount;
    private Map<Product, Integer> products;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product, quantity);
    }

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
