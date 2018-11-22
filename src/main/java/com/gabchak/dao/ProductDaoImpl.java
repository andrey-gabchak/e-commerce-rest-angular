package com.gabchak.dao;

import com.gabchak.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProduct(Product product) {
        jdbcTemplate.update("INSERT INTO PRODUCTS (NAME, PRICE, DESCRIPTION, FK_CATEGORIES) VALUES (?, ?, ?, ?)",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory());
    }

    @Override
    public Product findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT ID, NAME, PRICE, DESCRIPTION, FK_CATEGORIES FROM PRODUCTS WHERE ID = ?",
                new Object[] {id}, (rs, rowNum) -> new Product(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getLong("FK_CATEGORIES")
                ));
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update("UPDATE PRODUCTS SET NAME = ?, PRICE = ?, DESCRIPTION = ?, FK_CATEGORIES = ? WHERE ID = ?",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM PRODUCTS WHERE ID = ?", id);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT ID, NAME, PRICE, DESCRIPTION, FK_CATEGORIES FROM PRODUCTS",
                (rs, rowNum) -> new Product(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getLong("FK_CATEGORIES")
                ));
    }
}
