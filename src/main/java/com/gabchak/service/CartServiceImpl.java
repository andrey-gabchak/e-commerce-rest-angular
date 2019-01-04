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
    public void addProductToCart(Long userId, String productCode, Integer quantity) {
        cartDao.addToCart(userId, productCode, quantity);
    }

    @Override
    public Cart findCart(User user) {
        Map<Product, Integer> products = cartDao.findAllProductsOfUser(user.getId());

        Cart cart = new Cart();

        Double amount = 0.0;

        for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {
            cart.setProductAndQuantity(productEntry.getKey(), productEntry.getValue());
            amount += productEntry.getValue();
        }

        cart.setAmount(amount);
        cart.setUser(user);

        return cart;
    }

    @Override
    public void deleteProductByProductCode(Long userId, String productCode) {
        cartDao.deleteProductByProductCode(userId, productCode);
    }

    @Override
    public Cart setProductQuantity(Product product, Integer quantity, String categoryName, User user) {
        return null;
    }

    @Override
    public Cart deleteProduct(Product product, User user) {
        cartDao.deleteProductByProductCode(user.getId(), product.getProductCode());
        return cartDao.findAllProductsOfUser(user.getId());
    }
}
