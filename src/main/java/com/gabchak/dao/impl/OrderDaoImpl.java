package com.gabchak.dao.impl;

import com.gabchak.dao.OrderDao;
import com.gabchak.model.Order;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {


    @Override
    public long create(Order order) {
        return 0;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
