package com.gabchak.dao;

import com.gabchak.model.Cart;
import com.gabchak.model.User;

public interface CartDao {

    void saveOrUpdateCart(Cart cart);

    Cart findCart(User user);

    void deleteProductByProductCode(Long userId, String productCode);

}
