package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
@Component
public class Util implements ApplicationRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("1234");

        Role userRole = new Role("ROLE_USER");
        Role userAdmin = new Role("ROLE_ADMIN");

        List<Role> roles = List.of(userAdmin);

        roleService.save(userRole);
        roleService.save(userAdmin);

        User admin = new User();
        admin.setId(1);
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setUsername("Admin");
        admin.setPassword(password); //1234
        admin.setCity("AdminCity");
        admin.setAge(24);
        admin.setRoles(roles);
        userService.saveUser(admin);
    }

}
