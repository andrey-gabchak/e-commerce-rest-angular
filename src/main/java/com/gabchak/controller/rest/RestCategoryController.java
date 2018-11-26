package com.gabchak.controller.rest;

import com.gabchak.controller.external.model.CategoryDto;
import com.gabchak.model.Category;
import com.gabchak.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> collect = null;

        if (categoryService.findAll().isPresent()) {
            collect = categoryService.findAll().get().stream().map(CategoryDto::of).collect(toList());
        }
        return new ResponseEntity<>(collect, HttpStatus.NOT_FOUND);
    }
}
