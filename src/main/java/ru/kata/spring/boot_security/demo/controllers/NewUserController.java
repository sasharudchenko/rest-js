package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RegistrationService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/add")
public class NewUserController {
    private RegistrationService registrationService;
    private UserValidator userValidator;
    private UserService userService;
    @Autowired
    public NewUserController(RegistrationService registrationService, UserValidator userValidator,
                             UserService userService) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @PostMapping
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/newUser";
        }
        registrationService.register(user);
        return "redirect:/admin";
    }

    @GetMapping
    public String registrationPage(@ModelAttribute("user") User newUser, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("allUser", userService.allUsers());
        model.addAttribute("principal", user);
        model.addAttribute("count1", user.getRoles().size()== 1 );
        model.addAttribute("count2", user.getRoles().size()== 2 );
        return "newUser";
    }

}
