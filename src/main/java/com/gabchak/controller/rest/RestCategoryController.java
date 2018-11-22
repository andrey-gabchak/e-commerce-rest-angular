package com.gabchak.controller.rest;

import com.gabchak.controller.external.model.CategoryDto;
import com.gabchak.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class RestCategoryController {

    private CategoryService categoryService;

    @Autowired
    public RestCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/rest/categories")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return Optional.ofNullable(categoryService.findAll().stream()
                .map(CategoryDto::of)
                .collect(toList()))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
