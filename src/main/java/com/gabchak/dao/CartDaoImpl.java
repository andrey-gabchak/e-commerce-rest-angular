package com.gabchak.dao;

import com.gabchak.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartDaoImpl implements CartDao {

    private JdbcTemplate jdbcTemplate;
    private ProductDao productDao;

    @Autowired
    public CartDaoImpl(JdbcTemplate jdbcTemplate, ProductDao productDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDao = productDao;
    }

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        jdbcTemplate.update("INSERT INTO CARTS (FK_USER_ID, FK_PRODUCT_ID, PRODUCT_QUANTITY) VALUES (?, ?, ?)",
                userId,
                productId,
                quantity);
    }


    @Override
    public Map<Product, Integer> findAllUsersProducts(Long userId) {
        Map<Product, Integer> products = new HashMap<>();

        jdbcTemplate.query("SELECT FK_PRODUCT_ID, PRODUCT_QUANTITY FROM CARTS WHERE FK_USER_ID = ?",
                new Object[] {userId}, (rs, rowNum) -> products.put(
                        productDao.findById(rs.getLong(1)),
                        rs.getInt(2)
                ));
        return products;
    }


    @Override
    public void deleteProductById(Long userId, Long productId) {
        jdbcTemplate.update("DELETE FROM CARTS WHERE FK_USER_ID = ? AND FK_PRODUCT_ID = ?",
                userId,
                productId);
    }
}
