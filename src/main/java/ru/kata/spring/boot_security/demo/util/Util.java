package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;
@Component
public class Util implements ApplicationRunner {

    private UserServiceImpl userServiceImpl;

    private RoleServiceImpl roleServiceImpl;
    @Autowired
    public Util(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("1234");

        Role userUser = new Role("ROLE_USER");
        Role userAdmin = new Role("ROLE_ADMIN");

        List<Role> roleAdmin = List.of(userAdmin);
        List<Role> roleUser = List.of(userUser);
        List<Role> roleUserAndAdmin = List.of(userUser, userAdmin);

        roleServiceImpl.save(userUser);
        roleServiceImpl.save(userAdmin);

        User admin1 = new User();
        admin1.setId(1);
        admin1.setName("Admin");
        admin1.setSurname("Admin");
        admin1.setUsername("Admin");
        admin1.setPassword(password); //1234
        admin1.setCity("AdminCity");
        admin1.setAge(24);
        admin1.setRoles(roleAdmin);
        userServiceImpl.saveUser(admin1);

        User user1 = new User();
        user1.setId(2);
        user1.setName("User");
        user1.setSurname("User");
        user1.setUsername("User");
        user1.setPassword(password); //1234
        user1.setCity("UserCity");
        user1.setAge(22);
        user1.setRoles(roleUser);
        userServiceImpl.saveUser(user1);

        User userAndAdmin = new User();
        userAndAdmin.setId(3);
        userAndAdmin.setName("UA");
        userAndAdmin.setSurname("UA");
        userAndAdmin.setUsername("UA");
        userAndAdmin.setPassword(password); //1234
        userAndAdmin.setCity("UA");
        userAndAdmin.setAge(21);
        userAndAdmin.setRoles(roleUserAndAdmin);
        userServiceImpl.saveUser(userAndAdmin);
    }

}
