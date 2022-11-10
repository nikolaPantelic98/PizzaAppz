package com.app.pizzaorderingsystem.service;

import com.app.pizzaorderingsystem.entity.PizzaSize;

import java.util.List;
import java.util.Set;

public interface PizzaSizeService {

    List<PizzaSize> getAllPizzaSizes();
    PizzaSize findPizzaSizeById(Long pizzaSizeId);
    PizzaSize addNewPizzaSize(PizzaSize pizzaSize);
    PizzaSize updatePizzaSize(PizzaSize pizzaSize);
    void deletePizzaSizeById(Long pizzaSizeId);
}
