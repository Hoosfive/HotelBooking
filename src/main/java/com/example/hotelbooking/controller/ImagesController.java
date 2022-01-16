package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{user_id}")
    public String getUser(@PathVariable Long user_id, Model model) {
        model.addAttribute("users", usersService.get(user_id));
        return "users";
    }

    @GetMapping
    public String getUsers(Model model, @ModelAttribute(name = "errorMessage") String errorMessage) {
        model.addAttribute("users", usersService.getAll());
        if (errorMessage != null)
            if (!errorMessage.isEmpty())
                model.addAttribute("error", errorMessage);
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute(value = "user") User user, Model model) {
        usersService.save(user);
        model.addAttribute("users", usersService.getAll());
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute(value = "user") User user, Model model) {
        usersService.update(user);
        model.addAttribute("users", usersService.getAll());
        return "redirect:/users";
    }

    @PostMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable Long user_id, Model model) {
        usersService.remove(user_id);
        model.addAttribute("users", usersService.getAll());
        return "redirect:/users";
    }
}
