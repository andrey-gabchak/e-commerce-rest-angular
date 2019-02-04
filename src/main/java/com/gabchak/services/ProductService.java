package com.gabchak.services;

import com.gabchak.services.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    List<ProductDto> findAll();

    Optional<ProductDto> findById(Integer id);

    void deleteById(Integer id);
}
