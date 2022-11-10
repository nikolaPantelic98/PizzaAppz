package com.app.pizzaorderingsystem.controller;

import com.app.pizzaorderingsystem.entity.Pizza;
import com.app.pizzaorderingsystem.entity.PizzaSize;
import com.app.pizzaorderingsystem.service.impl.PizzaServiceImpl;
import com.app.pizzaorderingsystem.service.impl.PizzaSizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pos/pizzas/sizes")
public class PizzaSizeController {

    private final PizzaSizeServiceImpl pizzaSizeService;
    private final PizzaServiceImpl pizzaService;

    @Autowired
    public PizzaSizeController(PizzaSizeServiceImpl pizzaSizeService, PizzaServiceImpl pizzaService) {
        this.pizzaSizeService = pizzaSizeService;
        this.pizzaService = pizzaService;
    }

    // shows a list of pizza sizes
    @GetMapping
    public String listPizzaSizes(Model model) {
        model.addAttribute("pizzaSizes", pizzaSizeService.getAllPizzaSizes());
        return "pizza_sizes";
    }

    // form for creating a new pizza size
    @GetMapping("/new")
    public String addNewPizzaForm(Model model) {
        PizzaSize pizzaSize = new PizzaSize();
        List<Pizza> listPizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzaSize", pizzaSize);
        model.addAttribute("listPizzas", listPizzas);
        return "pizza_size_create";
    }

    // create a new pizza size
    @PostMapping
    public String addNewPizzaSize(@ModelAttribute("pizzaSize") PizzaSize pizzaSize) {
        pizzaSizeService.addNewPizzaSize(pizzaSize);
        return "redirect:/pos/pizzas";
    }

    // form for updating already existing pizza size
    @GetMapping("/edit/{pizzaSizeId}")
    public String updatePizzaSizeForm(@PathVariable Long pizzaSizeId, Model model) {
        List<Pizza> listPizzas = pizzaService.getAllPizzas();
        List<PizzaSize> listPizzaSizes = pizzaSizeService.getAllPizzaSizes();
        model.addAttribute("pizzaSize", pizzaSizeService.findPizzaSizeById(pizzaSizeId));
        model.addAttribute("listPizzas", listPizzas);
        model.addAttribute("listPizzaSizes", listPizzaSizes);
        return "pizza_size_edit";
    }

    // update already existing pizza size
    @PostMapping("/{pizzaSizeId}")
    public String updatePizzaSize(@PathVariable Long pizzaSizeId, @ModelAttribute("pizzaSize") PizzaSize pizzaSize) {
        // get room from database by id
        PizzaSize existingPizzaSize = pizzaSizeService.findPizzaSizeById(pizzaSizeId);
        existingPizzaSize.setPizzaSizeId(pizzaSizeId);
        existingPizzaSize.setName(pizzaSize.getName());
        existingPizzaSize.setPrice(pizzaSize.getPrice());
        existingPizzaSize.setPizza(pizzaSize.getPizza());

        // save updated room object
        pizzaSizeService.updatePizzaSize(existingPizzaSize);
        return "redirect:/pos/pizzas";
    }

    // view pizza size details for particular pizza size
    @GetMapping("/details/{pizzaSizeId}")
    public String viewPizzaSize(@PathVariable Long pizzaSizeId, Model model) {
        model.addAttribute("pizzaSize", pizzaSizeService.findPizzaSizeById(pizzaSizeId));
        return "pizza_size_view";
    }

    // handler method to handle delete pizza size request
    @GetMapping("/{pizzaSizeId}")
    public String deletePizzaSize(@PathVariable Long pizzaSizeId) {
        pizzaSizeService.deletePizzaSizeById(pizzaSizeId);
        return "redirect:/pos/pizzas";
    }
}
