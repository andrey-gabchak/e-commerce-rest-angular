package com.gabchak.services.impl;

import com.gabchak.dao.ProductDao;
import com.gabchak.models.Product;
import com.gabchak.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> findByProductCode(String productCode) {
        return productDao.findByProductCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {

    }
}
