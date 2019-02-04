package com.gabchak.services;

import com.gabchak.services.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    Optional<CategoryDto> findById(Integer id);

    List<CategoryDto> findAll();

    void deleteById(Integer id);
}
