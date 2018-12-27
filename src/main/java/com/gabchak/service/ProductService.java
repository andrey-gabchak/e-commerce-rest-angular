package com.gabchak.service;

import com.gabchak.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(Product product);

    void update(Product product);

    List<Product> findAll();

    Optional<Product> findByProductCode(String productCode);

    void deleteByProductCode(String productCode);
}
