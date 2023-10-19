package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.*;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.swing.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.ListResourceBundle;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userServiceImpl;
    private RoleService roleService;
    private UserValidator userValidator;
    private RegistrationService registrationService;
    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleService,
                           UserValidator userValidator, RegistrationService registrationService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
        this.userValidator = userValidator;
        this.registrationService = registrationService;
    }

    @GetMapping()
    public String allUsers(Model model, Principal principal) {
        User user = userServiceImpl.findByUsername(principal.getName());

        model.addAttribute("allUser", userServiceImpl.allUsers());
        model.addAttribute("principal", user);
        model.addAttribute("count1", user.getRoles().size()== 1 );
        model.addAttribute("count2", user.getRoles().size()== 2 );
        model.addAttribute("listRoles", roleService.listRoles());



//        model.addAttribute("user", userServiceImpl.allUsers().listIterator().next());
//        System.out.println(userServiceImpl.allUsers().listIterator().next());
//        for (User userFor : userServiceImpl.allUsers()) {
//            System.out.println(userFor.toString());
//        }






        return "admin.all";
    }

//    @GetMapping()
//    public String userById(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userServiceImpl.getById(id));
//        return "admin.userById";
//
//    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getById(id));
        model.addAttribute("listRoles", roleService.listRoles());

        return "admin.edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userServiceImpl.updateUser(user, id);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }




}
