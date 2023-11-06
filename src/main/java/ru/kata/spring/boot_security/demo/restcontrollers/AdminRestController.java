package ru.kata.spring.boot_security.demo.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/admin")
public class AdminRestController {
    private UserService userService;
    private RoleService roleService;
    private UserValidator userValidator;
    private RegistrationService registrationService;

    @Autowired
    public AdminRestController(UserServiceImpl userServiceImpl, RoleServiceImpl roleService,
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
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> edit(@PathVariable("id") Long id, Model model) {


        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody User user,
                                             @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        registrationService.register(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<User> getAuthUser(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
