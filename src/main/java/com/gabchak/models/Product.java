package com.gabchak.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gabchak.controllers.external.model.ProductDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private Double price;
    @JoinColumn(name = "FK_CATEGORIES")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Category category;

    public static Product of(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return product;
    }
}
