package com.gabchak.services.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private int id;
    private String name;
    private String description;
    private List<ProductDto> products;

}