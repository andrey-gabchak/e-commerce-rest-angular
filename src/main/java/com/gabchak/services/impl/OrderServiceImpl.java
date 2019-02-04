package com.gabchak.services.impl;

import com.gabchak.models.Order;
import com.gabchak.repositories.OrderRepository;
import com.gabchak.services.OrderService;
import com.gabchak.services.dto.OrderDto;
import com.gabchak.services.dto.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.map(orderDto, Order.class);
        return orderMapper.map(orderRepository.save(order), OrderDto.class);
    }

    @Override
    public Optional<OrderDto> findById(Integer id) {
        return orderRepository.findById(id)
                .map(order -> orderMapper.map(order, OrderDto.class));
    }

    @Override
    public List<OrderDto> findAll() {
        return orderMapper.mapAsList(orderRepository.findAll(), OrderDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }
}
