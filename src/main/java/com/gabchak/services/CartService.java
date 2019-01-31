package com.gabchak.services;

import com.gabchak.models.Cart;
import com.gabchak.models.Product;
import com.gabchak.models.User;

import java.util.Optional;

public interface CartService {

    Cart save(Cart cart);

    Optional<Cart> findByUser(User user);

    void deleteProductByProductCode(Long userId, String productCode);

    Cart deleteProduct(Product product, User user);
}
