package com.gabchak.dao;

import com.gabchak.model.Product;

import java.util.Map;

public interface CartDao {

    void addToCart(Long userId, Long productId, Integer quantity);

    Map<Product, Integer> findAllUsersProducts(Long userId);

    void deleteProductById(Long userId, Long productId);

}
