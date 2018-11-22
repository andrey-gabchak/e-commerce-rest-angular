package com.gabchak.service;

import com.gabchak.dao.CartDao;
import com.gabchak.model.Cart;
import com.gabchak.model.Product;
import com.gabchak.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        cartDao.addToCart(userId, productId, quantity);
    }

    @Override
    public Cart findAllUsersProducts(User user) {
        Map<Product, Integer> products = cartDao.findAllUsersProducts(user.getId());

        Cart cart = new Cart();

        Double amount = null;

        for (Product product : products.keySet()) {
            amount += product.getPrice();
        }

        cart.setProducts(products);
        cart.setAmount(amount);
        cart.setUser(user);

        return cart;
    }

    @Override
    public void deleteProductById(Long userId, Long productId) {
        cartDao.deleteProductById(userId, productId);
    }
}
