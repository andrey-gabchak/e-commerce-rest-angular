package com.gabchak.services.impl;

import com.gabchak.models.Cart;
import com.gabchak.models.Product;
import com.gabchak.models.User;
import com.gabchak.repositories.CartRepository;
import com.gabchak.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public void deleteProductByProductCode(Long userId, String productCode) {

    }

    @Override
    public Cart deleteProduct(Product product, User user) {
        return null;
    }

    /*
    @Override
    public void deleteProductByProductCode(Long userId, String productCode) {
        cartDao.deleteProductByProductCode(userId, productCode);
    }

    @Override
    public Cart deleteProduct(Product product, User user) {
        cartDao.deleteProductByProductCode(user.getId(), product.getProductCode());
        return cartDao.findByUser(user);
    }*/
}
