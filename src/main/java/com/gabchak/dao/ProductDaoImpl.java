package com.gabchak.dao;

import com.gabchak.model.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Override
    public void save(Product product) {
    }

    @Override
    public Optional<Product> findByProductCode(String productCode) {
        return Optional.empty();
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void update(Product product) {
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public List<Product> findAll() {
        return null;
    }
}
