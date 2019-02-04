package com.gabchak.services.dto;

import com.gabchak.models.User;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CartDto {

    private int id;
    private User user;
    private Double amount;
    private Map<ProductDto, Integer> products = new HashMap<>();
}
