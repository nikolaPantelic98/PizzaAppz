package com.app.pizzaorderingsystem.service;

import com.app.pizzaorderingsystem.entity.Pizza;
import com.app.pizzaorderingsystem.entity.PizzaSize;

import java.util.List;
import java.util.Set;

public interface PizzaService {

    List<Pizza> getAllPizzas();
    Pizza findPizzaById(Long pizzaId);
    Set<PizzaSize> findPizzaSizesByPizzaId(Long pizzaId);
    Pizza addNewPizza(Pizza pizza);
    Pizza updatePizza(Pizza pizza);
    void deletePizzaById(Long pizzaId);
}
