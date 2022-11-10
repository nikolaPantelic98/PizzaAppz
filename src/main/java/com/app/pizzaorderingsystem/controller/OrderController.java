package com.app.pizzaorderingsystem.controller;

import com.app.pizzaorderingsystem.entity.Order;
import com.app.pizzaorderingsystem.entity.Pizza;
import com.app.pizzaorderingsystem.service.impl.OrderServiceImpl;
import com.app.pizzaorderingsystem.service.impl.PizzaServiceImpl;
import com.app.pizzaorderingsystem.service.impl.PizzaSizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pos/orders")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final PizzaServiceImpl pizzaService;
    private final PizzaSizeServiceImpl pizzaSizeService;

    @Autowired
    public OrderController(OrderServiceImpl orderService, PizzaServiceImpl pizzaService, PizzaSizeServiceImpl pizzaSizeService) {
        this.orderService = orderService;
        this.pizzaService = pizzaService;
        this.pizzaSizeService = pizzaSizeService;
    }

    // shows a list of orders
    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    // form for creating a new order
    @GetMapping("/new/{pizzaId}")
    public String addNewOrderForm(@PathVariable Long pizzaId, Model model) {
        Order order = new Order();
        List<Pizza> listPizzas = pizzaService.getAllPizzas();
        model.addAttribute("order", order);
        model.addAttribute("listPizzas", listPizzas);
        model.addAttribute("listPizzaSizes", pizzaService.findPizzaSizesByPizzaId(pizzaId));
        return "order_create";
    }

    // create a new order
    @PostMapping
    public String addNewOrder(@ModelAttribute("order") Order order) {
        orderService.addNewOrder(order);
        return "order_success";
    }

    // view order details for particular order
    @GetMapping("/details/{orderId}")
    public String viewOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.findOrderById(orderId));
        return "order_view";
    }

    // handler method to handle delete order request
    @GetMapping("/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return "redirect:/pos/orders";
    }
}
