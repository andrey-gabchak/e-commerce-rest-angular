package com.gabchak.services;

import com.gabchak.services.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    Optional<OrderDto> findById(Integer id);

    List<OrderDto> findAll();

    void deleteById(Integer id);

}
