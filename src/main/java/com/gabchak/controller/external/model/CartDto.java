package com.gabchak.controller.external.model;

import com.gabchak.model.Cart;
import com.gabchak.model.CartDetails;
import com.gabchak.model.User;

import java.util.HashMap;
import java.util.Map;

public class CartDto {

    private User user;
    private Double amount;
    private Map<ProductDto, Integer> products = new HashMap<>();

    public static CartDto of(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setUser(cart.getUser());
        cartDto.setAmount(cart.getAmount());

        for (CartDetails cartDetails : cart.getCartDetails()) {
            cartDto.getProducts()
                    .put(ProductDto.of(cartDetails.getProduct()), cartDetails.getQuantity());
        }
        return cartDto;
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

    public Map<ProductDto, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductDto, Integer> products) {
        this.products = products;
    }
}
