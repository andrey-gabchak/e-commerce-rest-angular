package com.gabchak.model;

import com.gabchak.controller.external.model.CartDto;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> products = new HashMap<>();
    private User user;
    private Double amount;

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product, quantity);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public static Cart of(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setUser(cartDto.getUser());
        cart.setAmount(cartDto.getAmount());
        cart.setProducts(cartDto.getProducts());
        return cart;
    }
}
