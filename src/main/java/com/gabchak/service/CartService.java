package com.gabchak.service;

import com.gabchak.model.Cart;
import com.gabchak.model.Product;
import com.gabchak.model.User;

public interface CartService {

    void saveOrUpdateCart(Cart cart);

    Cart findCart(User user);

    void deleteProductByProductCode(Long userId, String productCode);

    Cart deleteProduct(Product product, User user);
}
