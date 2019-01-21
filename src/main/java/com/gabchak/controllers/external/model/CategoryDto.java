package com.gabchak.controllers.external.model;

import com.gabchak.models.Category;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryDto {

    private String name;
    private String description;
    private List<ProductDto> products;

    public static List<CategoryDto> of(List<Category> allCategories) {
        return allCategories.stream().map(CategoryDto::of).collect(Collectors.toList());
    }

    public static CategoryDto of(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setProducts(ProductDto.of(category.getProducts()));
        return categoryDto;
    }

}