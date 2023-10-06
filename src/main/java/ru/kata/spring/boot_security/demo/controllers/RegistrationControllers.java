package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RegistrationService;

import javax.imageio.spi.ServiceRegistry;

@Controller
@RequestMapping("/registration")
public class RegistrationControllers {
    @Autowired
    private RegistrationService registrationService;
    @GetMapping
    public String registrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }
    @PostMapping
    public String add(@ModelAttribute("user") User user ) {
        registrationService.register(user);
        return "redirect:/login";
    }
}


