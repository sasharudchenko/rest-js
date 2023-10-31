package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.*;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.swing.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    private UserValidator userValidator;
    private RegistrationService registrationService;
    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleService,
                           UserValidator userValidator, RegistrationService registrationService) {
        this.userService = userServiceImpl;
        this.roleService = roleService;
        this.userValidator = userValidator;
        this.registrationService = registrationService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> allUsers(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        JPasswordField field = new JPasswordField();

        model.addAttribute("allUser", userService.allUsers());
        model.addAttribute("principal", user);
        model.addAttribute("count1", user.getRoles().size()== 1 );
        model.addAttribute("count2", user.getRoles().size()== 2 );
        model.addAttribute("listRoles", roleService.listRoles());

        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

//    @GetMapping()
//    public String userById(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userServiceImpl.getById(id));
//        return "admin.userById";
//
//    }
    @GetMapping("/{id}/edit")
    public ResponseEntity<User> edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("listRoles", roleService.listRoles());

        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.updateUser(user, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        registrationService.register(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/add")
    public ResponseEntity<HttpStatus> registrationPage(@ModelAttribute("user") User newUser, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("allUser", userService.allUsers());
        model.addAttribute("principal", user);
        model.addAttribute("count1", user.getRoles().size()== 1 );
        model.addAttribute("count2", user.getRoles().size()== 2 );
        model.addAttribute("listRoles", roleService.listRoles());

        return ResponseEntity.ok(HttpStatus.OK);
    }




}
