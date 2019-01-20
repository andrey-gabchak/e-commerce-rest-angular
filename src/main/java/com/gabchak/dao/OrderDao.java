package com.gabchak.dao;


import com.gabchak.models.Order;

import java.util.List;

public interface OrderDao {

    long create(Order order);

    void update(Order order);

    void delete(Long id);

    Order findById(Long id);

    List<Order> findAll();

}
