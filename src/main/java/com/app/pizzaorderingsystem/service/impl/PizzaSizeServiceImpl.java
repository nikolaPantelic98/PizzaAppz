package com.app.pizzaorderingsystem.service.impl;

import com.app.pizzaorderingsystem.entity.PizzaSize;
import com.app.pizzaorderingsystem.repository.PizzaSizeRepository;
import com.app.pizzaorderingsystem.service.PizzaSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaSizeServiceImpl implements PizzaSizeService {

    private final PizzaSizeRepository pizzaSizeRepository;

    @Autowired
    public PizzaSizeServiceImpl(PizzaSizeRepository pizzaSizeRepository) {
        this.pizzaSizeRepository = pizzaSizeRepository;
    }


    @Override
    public List<PizzaSize> getAllPizzaSizes() {
        return pizzaSizeRepository.findAll();
    }

    @Override
    public PizzaSize findPizzaSizeById(Long pizzaSizeId) {
        return pizzaSizeRepository.findById(pizzaSizeId).get();
    }

    @Override
    public PizzaSize addNewPizzaSize(PizzaSize pizzaSize) {
        return pizzaSizeRepository.save(pizzaSize);
    }

    @Override
    public PizzaSize updatePizzaSize(PizzaSize pizzaSize) {
        return pizzaSizeRepository.save(pizzaSize);
    }

    @Override
    public void deletePizzaSizeById(Long pizzaSizeId) {
        pizzaSizeRepository.deleteById(pizzaSizeId);
    }
}
