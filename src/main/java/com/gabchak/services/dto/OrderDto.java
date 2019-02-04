package com.gabchak.services.dto;

import com.gabchak.models.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class OrderDto {

    private Integer orderId;
    private UserDto customer;
    private LocalDate orderDate;
    private String orderComment;
    private Double orderAmount;
    private Map<Product, Integer> products;
}
