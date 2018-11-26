package com.gabchak.controller.external.model;

import com.gabchak.model.Category;
import com.gabchak.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryDto {
    private String categoryName;

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryDto() {
    }

    public static CategoryDto of(Category category) {
        return new CategoryDto(category.getCategoryName());
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
