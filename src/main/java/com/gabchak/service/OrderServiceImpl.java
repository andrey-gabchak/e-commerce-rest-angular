package com.gabchak.service;

import com.gabchak.dao.OrderDao;
import com.gabchak.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public long create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

}
