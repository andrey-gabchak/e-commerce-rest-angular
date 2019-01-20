package com.gabchak.services;

import com.gabchak.models.Cart;
import com.gabchak.models.Product;
import com.gabchak.models.User;

public interface CartService {

    void saveOrUpdateCart(Cart cart);

    Cart findCart(User user);

    void deleteProductByProductCode(Long userId, String productCode);

    Cart deleteProduct(Product product, User user);
}
