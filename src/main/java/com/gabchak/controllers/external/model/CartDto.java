package com.gabchak.controllers.external.model;

import com.gabchak.models.Cart;
import com.gabchak.models.CartDetails;
import com.gabchak.models.User;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
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
}
