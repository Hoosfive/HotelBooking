package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{user_id}")
    public User getUser(@PathVariable Long user_id) {
        return usersService.get(user_id);
    }

    @GetMapping
    public List<User> getUsers() {
        return usersService.getAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return usersService.save(user);
    }

    @PutMapping
    public User editUser(@RequestBody User user) {
        return usersService.update(user);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable Long user_id) {
        usersService.remove(user_id);
    }
}
