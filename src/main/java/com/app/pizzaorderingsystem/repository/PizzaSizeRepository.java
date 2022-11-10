package com.app.pizzaorderingsystem.repository;

import com.app.pizzaorderingsystem.entity.PizzaSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaSizeRepository extends JpaRepository<PizzaSize, Long> {
}
