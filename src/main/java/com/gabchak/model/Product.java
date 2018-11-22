package com.gabchak.model;

import com.gabchak.controller.external.model.ProductDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "FK_CATEGORIES")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Product() {
    }

    public static Product of(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
