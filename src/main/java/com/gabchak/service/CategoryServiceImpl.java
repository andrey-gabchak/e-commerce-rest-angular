package com.gabchak.service;

import com.gabchak.dao.CategoryDao;
import com.gabchak.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void save(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public Optional<List<Category>> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public void deleteByName(String name) {
        categoryDao.deleteByName(name);
    }

    @Override
    public Optional<Category> findByNameAndListProducts(String name) {
        return categoryDao.findByNameWithProductList(name);
    }
}
