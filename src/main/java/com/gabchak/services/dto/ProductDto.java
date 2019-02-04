package com.gabchak.services.dto;

import lombok.Data;

@Data
public class ProductDto {

    private int id;
    private String productCode;
    private String name;
    private String description;
    private Double price;
    private String categoryName;
    private Integer quantity;
}