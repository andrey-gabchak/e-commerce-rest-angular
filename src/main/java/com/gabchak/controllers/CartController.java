package com.gabchak.controllers;

import com.gabchak.services.dto.CartDto;
import com.gabchak.services.dto.ProductDto;
import com.gabchak.models.Cart;
import com.gabchak.models.CartDetails;
import com.gabchak.models.Product;
import com.gabchak.models.User;
import com.gabchak.services.CartService;
import com.gabchak.services.UserService;
import com.gabchak.services.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PostMapping("/buy")
    public ResponseEntity<CartDto> addProductToCart(@RequestBody ProductDto productDto, Principal principal) {
        String name = principal.getName();
        Optional<UserDto> byEmail = userService.findByEmail(name);
        UserDto userDto = byEmail.orElse(null);
        boolean isCartPresent = cartService.findByUser(userDto).isPresent();
        Cart cart = new Cart();

        if (isCartPresent) {
            cart = addProductIfCartExist(productDto,
                    cartService.findByUser(userDto).get());
        }
        if (isCartPresent) {
            cart = createNewCart(productDto);
        }

        ;

        return Optional.of(cartService.save(cart))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping
    public ResponseEntity<CartDto> deleteProductFromCart(@RequestBody ProductDto productDto, UserDto userDto) {
        return Optional.of(cartService.deleteProduct(productDto, userDto))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    private CartDetails createCartDetails(ProductDto productDto, Cart cart) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setCart(cart);
        cartDetails.setProduct(Product.of(productDto));
        int defaultQuantity = 1;
        cartDetails.setQuantity(defaultQuantity);
        return cartDetails;
    }

    private Cart createNewCart(ProductDto productDto) {
        Cart cart;
        cart = new Cart();
        CartDetails cartDetail = createCartDetails(productDto, cart);
        cart.getCartDetails().add(cartDetail);
        return cart;
    }

    private Cart addProductIfCartExist(ProductDto productDto, Cart cart) {
        boolean isItNewProduct = true;
        for (CartDetails cartDetail : cart.getCartDetails()) {
            if (cartDetail.getProduct().equals(Product.of(productDto))) {
                int defaultQuantity = 1;
                cartDetail.setQuantity(cartDetail.getQuantity() + defaultQuantity);
                isItNewProduct = false;
            }
        }
        if (isItNewProduct) {
            CartDetails cartDetail = createCartDetails(productDto, cart);
            cart.getCartDetails().add(cartDetail);
        }
        return cart;
    }
}
