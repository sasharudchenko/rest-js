package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class ViewController {

    private UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String showUserPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("count1", user.getRoles().size() == 1);
        model.addAttribute("count2", user.getRoles().size() == 2);
        return "user.userById";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("count1", user.getRoles().size() == 1);
        model.addAttribute("count2", user.getRoles().size() == 2);
        return "admin";
    }

}
