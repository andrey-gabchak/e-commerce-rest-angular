package com.gabchak.service;

import com.gabchak.dao.CartDao;
import com.gabchak.model.Cart;
import com.gabchak.model.Product;
import com.gabchak.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void saveOrUpdateCart(Cart cart) {
        cartDao.saveOrUpdateCart(cart);
    }

    @Override
    public Cart findCart(User user) {
        return cartDao.findCart(user);
    }

    @Override
    public void deleteProductByProductCode(Long userId, String productCode) {
        cartDao.deleteProductByProductCode(userId, productCode);
    }

    @Override
    public Cart deleteProduct(Product product, User user) {
        cartDao.deleteProductByProductCode(user.getId(), product.getProductCode());
        return cartDao.findCart(user);
    }
}
