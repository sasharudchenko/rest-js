package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RegistrationServiceImpl;

@Controller
@RequestMapping("/registration")
public class RegistrationControllers {

    private RegistrationServiceImpl registrationService;
    @Autowired
    public RegistrationControllers(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

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


