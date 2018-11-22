package com.gabchak.service;

import com.gabchak.model.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    Product findById(Long id);

    void update(Product product);

    void deleteById(Long id);

    List<Product> findAll();
}
