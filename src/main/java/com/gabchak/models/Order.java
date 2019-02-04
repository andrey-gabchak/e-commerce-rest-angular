package com.gabchak.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
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
}
