package com.gabchak.dao;

import com.gabchak.model.Category;

import java.util.List;

public interface CategoryDao {

    void addCategory(Category category);

    void update(Category category);

    Category findById(Long id);

    Category findByIdWithProductList(Long id);

    Category findByName(String name);

    List<Category> findAll();

    void deleteById(Long id);

}
