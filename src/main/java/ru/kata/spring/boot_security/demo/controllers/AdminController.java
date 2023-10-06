package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String allUsers(Model model) {
        model.addAttribute("allUser", userService.allUsers());

        return "admin.all";
    }

//    @GetMapping("/user/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "admin.new";
//    }
//
//    @PostMapping
//    public String add(@ModelAttribute("user") User user ) {
//        userService.add(user);
//        return "redirect:/user";
//    }

    @GetMapping("/user/{id}")
    public String userById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "admin.userById";

    }
    @GetMapping("/user/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin.edit";
    }
    @PatchMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.updateUser(user, id);
        return "redirect:/admin/user";
    }
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

}
