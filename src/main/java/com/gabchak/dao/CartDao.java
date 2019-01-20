package com.gabchak.dao;

import com.gabchak.models.Cart;
import com.gabchak.models.User;

public interface CartDao {

    void saveOrUpdateCart(Cart cart);

    Cart findCart(User user);

    void deleteProductByProductCode(Long userId, String productCode);

}
