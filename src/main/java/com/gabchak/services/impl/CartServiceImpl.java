package com.gabchak.services.impl;

import com.gabchak.models.Cart;
import com.gabchak.models.User;
import com.gabchak.repositories.CartRepository;
import com.gabchak.services.CartService;
import com.gabchak.services.dto.CartDto;
import com.gabchak.services.dto.ProductDto;
import com.gabchak.services.dto.UserDto;
import com.gabchak.services.dto.mappers.CartMapper;
import com.gabchak.services.dto.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private UserMapper userMapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           CartMapper cartMapper,
                           UserMapper userMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userMapper = userMapper;
    }

    @Override
    public CartDto save(CartDto cartDto) {
        Cart cart = cartMapper.map(cartDto, Cart.class);
        return cartMapper.map(cartRepository.save(cart), CartDto.class);
    }

    @Override
    public Optional<CartDto> findByUser(UserDto userDto) {
        User user = userMapper.map(userDto, User.class);
        return cartRepository.findByUser(user)
                .map(cart -> cartMapper.map(cart, CartDto.class));
    }

    @Override
    public CartDto deleteProduct(ProductDto productDto, UserDto userDto) {
        return null;
    }
}
