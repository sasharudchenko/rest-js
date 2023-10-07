package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.RegistrationService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
public class RegistrationControllers {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping
    public String registrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }
    @PostMapping
    public String add(@ModelAttribute("user") User user) {
        registrationService.register(user);
        return "redirect:/login";
    }
}


