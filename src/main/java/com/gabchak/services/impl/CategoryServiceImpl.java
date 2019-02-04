package com.gabchak.services.impl;

import com.gabchak.models.Category;
import com.gabchak.repositories.CategoryRepository;
import com.gabchak.services.CategoryService;
import com.gabchak.services.dto.CategoryDto;
import com.gabchak.services.dto.ProductDto;
import com.gabchak.services.dto.mappers.CategoryMapper;
import com.gabchak.services.dto.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private ProductMapper productMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper,
                               ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
    }



    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.map(categoryDto, Category.class);
        Category saved = categoryRepository.save(category);
        return categoryMapper.map(saved, CategoryDto.class);
    }

    @Override
    public Optional<CategoryDto> findById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        return optionalCategory
                .map(this::getCategoryDtoWithProducts);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::getCategoryDtoWithProducts)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto getCategoryDtoWithProducts(Category category) {
        CategoryDto categoryDto = categoryMapper.map(category, CategoryDto.class);
        List<ProductDto> productDtos = productMapper.mapAsList(category.getProducts(), ProductDto.class);
        categoryDto.setProducts(productDtos);
        return categoryDto;
    }
}
