package com.gabchak.service;

import com.gabchak.model.Cart;
import com.gabchak.model.User;

public interface CartService {

    void addToCart(Long userId, Long productId, Integer quantity);

    Cart findAllUsersProducts(User user);

    void deleteProductById(Long userId, Long productId);
}
