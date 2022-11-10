package com.app.pizzaorderingsystem.controller;

import com.app.pizzaorderingsystem.entity.Role;
import com.app.pizzaorderingsystem.entity.User;
import com.app.pizzaorderingsystem.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pos/users")
public class UserController {

    private final CustomUserDetailsServiceImpl userService;

    @Autowired
    public UserController(CustomUserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    // shows a list of users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    // view user details for particular user
    @GetMapping("/details/{userId}")
    public String viewUser(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        return "user_view";
    }

    // handler method to handle delete user request
    @GetMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/pos/users";
    }

    // form for creating a new user from app
    @GetMapping("/new")
    public String registerNewUserFormFromApp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_create";
    }

    // create a new user from app
    @PostMapping("/new")
    public String registerNewUserFromApp(@ModelAttribute("user") User user) {
        userService.registerNewUser(user);
        return "redirect:/pos/users";
    }

    // form for updating already existing user
    @GetMapping("/edit/{userId}")
    public String updateUserForm(@PathVariable Long userId, Model model) {
        User user = userService.findUserById(userId);
        List<Role> listRoles = userService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_edit";
    }

    // assign role for user and then save user
    @PostMapping("/edit/save")
    public String saveUser(User user) {
        userService.saveUserWithRole(user);
        return "redirect:/pos/users";
    }
}
