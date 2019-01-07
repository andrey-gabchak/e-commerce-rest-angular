package com.gabchak.controller.rest;

import com.gabchak.controller.external.model.CartDto;
import com.gabchak.controller.external.model.ProductDto;
import com.gabchak.model.Cart;
import com.gabchak.model.CartDetails;
import com.gabchak.model.Product;
import com.gabchak.model.User;
import com.gabchak.service.CartService;
import com.gabchak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/rest/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PostMapping("/buy")
    public ResponseEntity<CartDto> addProductToCart(@RequestBody ProductDto productDto, Principal principal) {
        String name = principal.getName();
        Optional<User> byEmail = userService.findByEmail(name);
        User user = byEmail.orElse(null);
        Cart cart = cartService.findCart(user);

        if (cart != null) {
            boolean isItNewProduct = true;
            for (CartDetails cartDetail : cart.getCartDetails()) {
                if (cartDetail.getProduct().equals(Product.of(productDto))) {
                    cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                    isItNewProduct = false;
                }
            }
            if (isItNewProduct) {
                CartDetails cartDetail = createCartDetails(productDto, cart);
                cart.getCartDetails().add(cartDetail);
            }
        }
        if (cart == null) {
            cart = new Cart();
            CartDetails cartDetail = createCartDetails(productDto, cart);
            cart.getCartDetails().add(cartDetail);
        }

        cartService.saveOrUpdateCart(cart);

        return ResponseEntity.ok(CartDto.of(cart));
    }

    @DeleteMapping
    public ResponseEntity<CartDto> deleteProductFromCart(@RequestBody ProductDto productDto, User user) {
        Cart cart = cartService.deleteProduct(Product.of(productDto), user);
        return ResponseEntity.ok(CartDto.of(cart));
    }

    private CartDetails createCartDetails(ProductDto productDto, Cart cart) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setCart(cart);
        cartDetails.setProduct(Product.of(productDto));
        int defaultQuantity = 1;
        cartDetails.setQuantity(defaultQuantity);
        return cartDetails;
    }
}
