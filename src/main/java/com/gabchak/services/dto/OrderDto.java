package com.gabchak.services.dto;

import com.gabchak.models.Product;
import com.gabchak.models.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class OrderDto {

    private int id;
    private Long orderId;
    private User customer;
    private LocalDate orderDate;
    private String orderComment;
    private Double orderAmount;
    private Map<Product, Integer> products;
}
