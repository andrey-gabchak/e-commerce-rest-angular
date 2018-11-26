package com.gabchak.dao;

import com.gabchak.model.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Map;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {


    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {

    }

    @Override
    public Map<Product, Integer> findAllUsersProducts(Long userId) {
        return null;
    }

    @Override
    public void deleteProductById(Long userId, Long productId) {

    }
}
