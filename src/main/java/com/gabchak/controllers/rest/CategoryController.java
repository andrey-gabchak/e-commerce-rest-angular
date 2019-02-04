package com.gabchak.controllers.rest;

import com.gabchak.controllers.external.model.CategoryDto;
import com.gabchak.models.Category;
import com.gabchak.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return Optional.of(categoryService.findAll())
                .map(CategoryDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(CategoryDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.save(Category.of(categoryDto));
        return Optional.of(CategoryDto.of(category))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }


    @PutMapping
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.save(Category.of(categoryDto));
        return Optional.of(CategoryDto.of(category))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/{categoryName}/products")
    public ResponseEntity<CategoryDto> findByNameWithListProducts(@PathVariable String categoryName) {
        return categoryService.findByNameAndListProducts(categoryName)
                .map(CategoryDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}