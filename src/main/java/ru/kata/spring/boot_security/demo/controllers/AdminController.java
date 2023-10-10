package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;
import java.util.ListResourceBundle;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userServiceImpl;
    private RoleServiceImpl roleService;
    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String allUsers(Model model) {
        model.addAttribute("allUser", userServiceImpl.allUsers());

        return "admin.all";
    }

    @GetMapping("/user/{id}")
    public String userById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getById(id));
        return "admin.userById";

    }
    @GetMapping("/user/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getById(id));
        model.addAttribute("listRoles", roleService.listRoles());

        return "admin.edit";
    }
    @PatchMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userServiceImpl.updateUser(user, id);
        return "redirect:/admin/user";
    }
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin/user";
    }

}
