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
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category findByIdWithProductList(Long id) {
        return categoryDao.findByIdWithProductList(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public Optional<List<Category>> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryDao.deleteById(id);
    }
}
