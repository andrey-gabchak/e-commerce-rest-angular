package com.gabchak.controller;

import com.gabchak.controller.external.model.CartDto;
import com.gabchak.model.Cart;
import com.gabchak.model.Product;
import com.gabchak.model.User;
import com.gabchak.service.CartService;
import com.gabchak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    private UserService userService;
    private CartService cartService;

    @Autowired
    public CartController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping("/buy")
    public ModelAndView addToCart(HttpServletRequest request, Product product, Integer quantity, ModelAndView vm) {
        Cookie[] cookies = request.getCookies();

        User user = userService.findUserByCookies(cookies);
        if (user != null) {
            cartService.addToCart(user.getId(), product.getId(), quantity);

            vm.setViewName("cart");
            vm.addObject("cart", cartService.findAllUsersProducts(user));
        } else {
            vm.setViewName("product");
        }

        return vm;
    }

    @GetMapping("/cart")
    public ModelAndView showCartPage(HttpServletRequest request, ModelAndView vm) {
        Cookie[] cookies = request.getCookies();

        User user = userService.findUserByCookies(cookies);

        vm.setViewName("product");

        if (user != null) {
            Cart cart = cartService.findAllUsersProducts(user);
            CartDto cartDto = CartDto.of(cart);
            vm.addObject("cart", cartDto);
        }

        return vm;
    }
}
