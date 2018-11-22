package com.gabchak.controller.external.model;

import com.gabchak.model.Cart;
import com.gabchak.model.Product;
import com.gabchak.model.User;

import java.util.HashMap;
import java.util.Map;

public class CartDto {

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

    public static CartDto of(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setAmount(cart.getAmount());
        cartDto.setProducts(cart.getProducts());
        cartDto.setUser(cart.getUser());
        return cartDto;
    }
}
