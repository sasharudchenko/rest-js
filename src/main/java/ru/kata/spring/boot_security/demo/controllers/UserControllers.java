package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
public class UserControllers {

    private UserService userServiceImpl;
    private RoleService roleService;
    @Autowired
    public UserControllers(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userByUsername(Model model, Principal principal) {
        User user = userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("allUser", userServiceImpl.allUsers());
        model.addAttribute("principal", user);
        model.addAttribute("count1", user.getRoles().size()== 1 );
        model.addAttribute("count2", user.getRoles().size()== 2 );
        return "user.userById";
    }
}
