package com.app.pizzaorderingsystem.service;

import com.app.pizzaorderingsystem.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    Order findOrderById(Long orderId);
    Order addNewOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrderById(Long orderId);
}
