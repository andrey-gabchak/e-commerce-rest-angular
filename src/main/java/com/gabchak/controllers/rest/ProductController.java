package com.gabchak.controllers.rest;

import com.gabchak.controllers.external.model.CategoryDto;
import com.gabchak.controllers.external.model.ProductDto;
import com.gabchak.models.Product;
import com.gabchak.services.CategoryService;
import com.gabchak.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    private ResponseEntity<CategoryDto> getAllProductsByCategory(@PathVariable String categoryName) {
        return categoryService.findByNameAndListProducts(categoryName)
                .map(CategoryDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<ProductDto> getProductByProductCode(@PathVariable String productCode) {
        return productService.findByProductCode(productCode)
                .map(ProductDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveProduct(@RequestBody ProductDto productDto) {
        productService.save(Product.of(productDto));
        return getAllProductsByCategory(productDto.getCategoryName());
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<CategoryDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String productCode) {
        productDto.setProductCode(productCode);
        productService.save(Product.of(productDto));
        return getAllProductsByCategory(productDto.getCategoryName());
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<CategoryDto> deleteByProductCode(@PathVariable String productCode, String categoryName) {
        productService.deleteByProductCode(productCode);
        return getAllProductsByCategory(categoryName);
    }
}
