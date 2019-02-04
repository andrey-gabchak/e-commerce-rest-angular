package com.gabchak.services;

import com.gabchak.services.dto.CartDto;
import com.gabchak.services.dto.ProductDto;
import com.gabchak.services.dto.UserDto;

import java.util.Optional;

public interface CartService {

    CartDto save(CartDto cartDto);

    Optional<CartDto> findByUser(UserDto userDto);

    void deleteProductByProductCode(Integer userId, String productCode);

    CartDto deleteProduct(ProductDto product, UserDto user);
}
