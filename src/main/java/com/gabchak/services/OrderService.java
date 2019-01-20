package com.gabchak.services;

import com.gabchak.models.Order;

import java.util.List;

public interface OrderService {

    long create(Order order);

    void update(Order order);

    void delete(Long id);

    Order findById(Long id);

    List<Order> findAll();

}
