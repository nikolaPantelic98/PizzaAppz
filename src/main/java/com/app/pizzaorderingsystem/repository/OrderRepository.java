package com.app.pizzaorderingsystem.repository;

import com.app.pizzaorderingsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
