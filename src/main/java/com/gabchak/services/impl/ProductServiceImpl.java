package com.gabchak.services.impl;

import com.gabchak.models.Product;
import com.gabchak.repositories.ProductRepository;
import com.gabchak.services.ProductService;
import com.gabchak.services.dto.ProductDto;
import com.gabchak.services.dto.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }



    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.map(productDto, Product.class);
        return productMapper.map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public List<ProductDto> findAll() {
        return productMapper.mapAsList(productRepository.findAll(), ProductDto.class);
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        return productRepository.findById(id)
                .map(product -> productMapper.map(product, ProductDto.class));
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

}
