package com.gabchak.service;

import com.gabchak.dao.ProductDao;
import com.gabchak.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
}
