package com.gabchak.service;

import com.gabchak.model.Cart;
import com.gabchak.model.Product;
import com.gabchak.model.User;

public interface CartService {

    void addProductToCart(Long userId, String productCode, Integer quantity);

    Cart findCart(User user);

    void deleteProductByProductCode(Long userId, String productCode);

    Cart setProductQuantity(Product product, Integer quantity, String categoryName, User user);

    Cart deleteProduct(Product product, User user);
}
