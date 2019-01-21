package com.gabchak.controllers.external.model;

import com.gabchak.models.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDto {

    private String productCode;
    private String name;
    private String description;
    private Double price;
    private String categoryName;
    private Integer quantity;

    public static ProductDto of(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductCode(product.getProductCode());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategoryName(product.getCategory().getName());
        return productDto;
    }

    public static List<ProductDto> of(List<Product> products) {
        return products.stream()
                .map(ProductDto::of)
                .collect(Collectors.toList());
    }

    public static ProductDto empty() {
        return new ProductDto();
    }
}