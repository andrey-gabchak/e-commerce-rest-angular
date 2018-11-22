package com.gabchak.model;

import com.gabchak.controller.external.model.OrderDto;

import java.time.LocalDate;
import java.util.Map;

public class Order {

    private Long orderId;
    private User customer;
    private LocalDate orderDate;
    private String orderComment;
    private Double orderAmount;
    private Map<Product, Integer> products;

    public Order() {
    }

    public Order(Long orderId, User customer, LocalDate orderDate, String orderComment, Double orderAmount) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderComment = orderComment;
        this.orderAmount = orderAmount;
    }

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
