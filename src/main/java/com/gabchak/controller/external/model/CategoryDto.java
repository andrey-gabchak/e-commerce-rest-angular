package com.gabchak.controller.external.model;

import com.gabchak.model.Category;
import com.gabchak.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryDto {

    @NotNull
    private String name;
    private Long id;
    private List<Product> products;

    private CategoryDto() {
    }

    private CategoryDto(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static CategoryDto empty() {
        return new CategoryDto();
    }

    public static CategoryDto of(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getProducts()
        );
    }
}
