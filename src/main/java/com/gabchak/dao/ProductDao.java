package com.gabchak.dao;

import com.gabchak.model.Product;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);

    Product findById(Long id);

    void update(Product product);

    void deleteById(Long id);

    List<Product> findAll();
}
