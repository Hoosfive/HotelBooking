package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Role;
import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.service.ImagesService;
import com.example.hotelbooking.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    private final ImagesService imagesService;

    public UserController(UsersService usersService, ImagesService imagesService) {
        this.usersService = usersService;
        this.imagesService = imagesService;
    }

    @GetMapping("/{user_id}")
    public String getUser(@PathVariable Long user_id, Model model) {
        model.addAttribute("users", usersService.get(user_id));
        return "users";
    }

    @GetMapping
    public String getUsers(Model model, @ModelAttribute(name = "errorMessage") String errorMessage) {
        model.addAttribute("users", usersService.getAll());
        if (errorMessage != null) if (!errorMessage.isEmpty())
            model.addAttribute("error", errorMessage);
        User new_user = new User();
//        Boolean isUserAdmin = false;
        List<Role> possible_roles = usersService.getAllRoles();
        model.addAttribute("new_user", new_user);
        model.addAttribute("possible_roles", possible_roles);
//        model.addAttribute("is_user_admin", isUserAdmin);
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute(value = "new_user") User user,
//                          @ModelAttribute(value = "is_user_admin") Boolean isUserAdmin,
                          @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.getSize() > 0 && !file.getName().isEmpty())
            user.setImage(imagesService.save(file));
        user.setRoles(new HashSet<>(usersService.getAllRoles()));
        user.syncRolesIdsWithRoles();
        /*Set<Role> roleSet = new HashSet<>();
        roleSet.add(usersService.getRoleByName("ROLE_USER"));
        if (isUserAdmin) {
            roleSet.add(usersService.getRoleByName("ROLE_ADMIN"));
        }
        user.setRoles(roleSet);*/

        usersService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute(value = "user") User user) {
        usersService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable Long user_id) {
        usersService.remove(user_id);
        return "redirect:/users";
    }

    @PostMapping("/{user_id}/addImage")
    public String addUserImage(@PathVariable Long user_id, @RequestParam(value = "file") MultipartFile file) {
        if (file.getSize() > 0 && !file.getName().isEmpty()) {
            User user = usersService.get(user_id);
            user.setImage(imagesService.save(file));
            usersService.update(user);
        }
        return "redirect:/rooms";
    }
}
