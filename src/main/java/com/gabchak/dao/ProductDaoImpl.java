package com.gabchak.dao;

import com.gabchak.model.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {


    @Override
    public void addProduct(Product product) {

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
