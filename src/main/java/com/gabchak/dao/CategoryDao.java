package com.gabchak.dao;

import com.gabchak.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    void addCategory(Category category);

    void update(Category category);

    Optional<Category> findById(Long id);

    Category findByIdWithProductList(Long id);

    Optional<Category> findByName(String name);

    Optional<List<Category>> findAll();

    Optional<Category> findByNameWithProductList(String name);

    void deleteByName(String name);
}
