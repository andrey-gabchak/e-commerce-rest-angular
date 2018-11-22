package com.gabchak.controller;

import com.gabchak.controller.external.model.OrderDto;
import com.gabchak.model.Cart;
import com.gabchak.model.Order;
import com.gabchak.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/admin/orders/delete_{id}")
    public ModelAndView deleteOrder(@PathVariable Long id, ModelAndView vm) {
        orderService.delete(id);
        vm.setViewName("OrdersList");
        vm.addObject("orders", orderService.findAll());
        return vm;
    }

    @GetMapping("/admin/orders")
    public ModelAndView showOrders(ModelAndView vm) {
        vm.setViewName("orders");
        List<Order> allOrders = orderService.findAll();
        List<OrderDto> allOrdersDto = new ArrayList<>();
        for (Order order : allOrders) {
            allOrdersDto.add(OrderDto.of(order));
        }
        vm.addObject("orders", allOrdersDto);
        return vm;
    }

    @GetMapping("/admin/orders/edit_{id}")
    public ModelAndView showEditOrderPage(@PathVariable Long id, ModelAndView vm) {
        vm.setViewName("orderEdit");
        Order order = orderService.findById(id);
        vm.addObject("order", OrderDto.of(order));
        return vm;
    }

    @PostMapping("/admin/orders/save_{id}")
    public ModelAndView saveOrder(@PathVariable Long id, OrderDto orderDto, ModelAndView vm) {
        vm.setViewName("orders");
        orderService.update(Order.of(orderDto));
        vm.addObject("orders", orderService.findAll());
        return vm;
    }

    @PostMapping("/cart/confirm")
    public ModelAndView createOrder(Cart cart, String comment, ModelAndView vm) {

        Order order = new Order();
        order.setProducts(cart.getProducts());
        order.setCustomer(cart.getUser());
        order.setOrderAmount(cart.getAmount());
        order.setOrderComment(comment);

        Long orderId = orderService.create(order);

        vm.setViewName("thanksForOrder");
        vm.addObject("orderId", orderId);
        return vm;
    }

}
