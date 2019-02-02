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
    public ResponseEntity<List<CategoryDto>> saveCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.save(Category.of(categoryDto));
        return getAllCategory();
    }


    @PutMapping
    public ResponseEntity<List<CategoryDto>> updateCategory(@RequestBody CategoryDto category, @PathVariable String name) {
        Category inputCategory = Category.of(category);
        Optional<Category> oldCategory = categoryService.findByName(name);
        if (oldCategory.isPresent()) {
            Category tempCategory = oldCategory.get();
            tempCategory.setName(inputCategory.getName());
            categoryService.save(tempCategory);
        }
        return getAllCategory();
    }

    @DeleteMapping("/{categoryName}")
    public ResponseEntity<List<CategoryDto>> deleteByName(@PathVariable String categoryName) {
        categoryService.deleteByName(categoryName);
        return getAllCategory();
    }

    @GetMapping("/{categoryName}/products")
    public ResponseEntity<CategoryDto> findByNameWithListProducts(@PathVariable String categoryName) {
        return categoryService.findByNameAndListProducts(categoryName)
                .map(CategoryDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}