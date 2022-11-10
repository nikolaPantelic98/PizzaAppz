package com.app.pizzaorderingsystem.controller;

import com.app.pizzaorderingsystem.entity.Pizza;
import com.app.pizzaorderingsystem.entity.PizzaSize;
import com.app.pizzaorderingsystem.service.impl.PizzaServiceImpl;
import com.app.pizzaorderingsystem.service.impl.PizzaSizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/pos/pizzas")
public class PizzaController {

    private final PizzaServiceImpl pizzaService;
    private final PizzaSizeServiceImpl pizzaSizeService;

    @Autowired
    public PizzaController(PizzaServiceImpl pizzaService, PizzaSizeServiceImpl pizzaSizeService) {
        this.pizzaService = pizzaService;
        this.pizzaSizeService = pizzaSizeService;
    }

    // shows a list of pizzas
    @GetMapping
    public String listPizzas(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "pizzas";
    }

    // shows a list of pizzas to customers
    @GetMapping("/customers")
    public String listPizzasForCustomers(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "pizzas_for_customers";
    }

    // shows a list of pizza sizes sorted by pizza
    @GetMapping("/{pizzaId}/sizes")
    public String listPizzaSizesByPizza(@PathVariable Long pizzaId, Model model) {
        model.addAttribute("pizzaSizes", pizzaService.findPizzaSizesByPizzaId(pizzaId));
        return "pizza_sizes_by_pizza";
    }

    // form for creating a new pizza
    @GetMapping("/new")
    public String addNewPizzaForm(Model model) {
        Pizza pizza = new Pizza();
        List<PizzaSize> listPizzaSizes = pizzaSizeService.getAllPizzaSizes();
        model.addAttribute("pizza", pizza);
        model.addAttribute("listPizzaSizes", listPizzaSizes);
        return "pizza_create";
    }

    // create a new pizza with image file
    @PostMapping
    public String addNewPizza(@ModelAttribute("pizza") Pizza pizza, @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        pizza.setImage(fileName);
        Pizza savedPizza = pizzaService.addNewPizza(pizza);
        String uploadDir = "./pizza-images/" + savedPizza.getPizzaId();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }

        return "redirect:/pos/pizzas";
    }

    // form for updating already existing pizza
    @GetMapping("/edit/{pizzaId}")
    public String updatePizzaForm(@PathVariable Long pizzaId, Model model) {
        model.addAttribute("pizza", pizzaService.findPizzaById(pizzaId));
        return "pizza_edit";
    }

    // update already existing pizza
    @PostMapping("/{pizzaId}")
    public String updatePizza(@PathVariable Long pizzaId, @ModelAttribute("pizza") Pizza pizza) {
        // get pizza from database by id
        Pizza existingPizza = pizzaService.findPizzaById(pizzaId);
        existingPizza.setPizzaId(pizzaId);
        existingPizza.setName(pizza.getName());
        existingPizza.setDescription(pizza.getDescription());
        existingPizza.setPizzaSizes(pizza.getPizzaSizes());

        // save updated pizza object
        pizzaService.updatePizza(existingPizza);
        return "redirect:/pos/pizzas";
    }

    // handler method to handle delete pizza request
    @GetMapping("/{pizzaId}")
    public String deletePizza(@PathVariable Long pizzaId) {
        pizzaService.deletePizzaById(pizzaId);
        return "redirect:/pos/pizzas";
    }

    // view pizza details for particular pizza
    @GetMapping("/details/{pizzaId}")
    public String viewPizza(@PathVariable Long pizzaId, Model model) {
        model.addAttribute("pizza", pizzaService.findPizzaById(pizzaId));
        return "pizza_view";
    }

    // view pizza details for particular pizza before ordering - cutomers
    @GetMapping("/customers/details/{pizzaId}")
    public String viewPizzaForCustomers(@PathVariable Long pizzaId, Model model) {
        model.addAttribute("pizza", pizzaService.findPizzaById(pizzaId));
        model.addAttribute("pizzaSizes", pizzaService.findPizzaSizesByPizzaId(pizzaId));
        return "pizza_view_for_customers";
    }
}
