package com.gabchak.dao;

import com.gabchak.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Product findById(Long id);

    void update(Product product);

    void deleteById(Long id);

    List<Product> findAll();

    void save(Product product);

    Optional<Product> findByProductCode(String productCode);
}
