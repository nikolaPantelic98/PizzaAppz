package com.app.pizzaorderingsystem.repository;

import com.app.pizzaorderingsystem.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
