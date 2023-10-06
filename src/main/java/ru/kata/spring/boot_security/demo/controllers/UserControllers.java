package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class UserControllers {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public String userByUsername(@PathVariable("id") long id, Model model, Principal principal) {

        model.addAttribute("user", userService.findByUsername(principal.getName()));
        //model.addAttribute("user", userService.getUser(id));
        return "user.userById";
    }
//    @GetMapping("/user/{id}")
//    public String userById(@PathVariable("id") long id, Model model, Principal principal) {
//
//       // model.addAttribute("user", userService.findByUsername(principal.getName()));
//        model.addAttribute("user", userService.getUser(id));
//        return "user.userById";
//    }

}
