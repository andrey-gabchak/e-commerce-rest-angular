package com.gabchak.service;

import com.gabchak.model.Cart;
import com.gabchak.model.User;

public interface CartService {

    void addProductToCart(Long userId, String productCode, Integer quantity);

    Cart findAllUserProducts(User user);

    void deleteProductByProductCode(Long userId, String productCode);
}
