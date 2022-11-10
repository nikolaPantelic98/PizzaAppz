package com.app.pizzaorderingsystem.service.impl;

import com.app.pizzaorderingsystem.entity.Pizza;
import com.app.pizzaorderingsystem.entity.PizzaSize;
import com.app.pizzaorderingsystem.repository.PizzaRepository;
import com.app.pizzaorderingsystem.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza findPizzaById(Long pizzaId) {
        return pizzaRepository.findById(pizzaId).get();
    }

    @Override
    public Set<PizzaSize> findPizzaSizesByPizzaId(Long pizzaId) {
        Pizza pizza = pizzaRepository.findById(pizzaId).get();
        return pizza.getPizzaSizes();
    }

    @Override
    public Pizza addNewPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza updatePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public void deletePizzaById(Long pizzaId) {
        pizzaRepository.deleteById(pizzaId);
    }
}
