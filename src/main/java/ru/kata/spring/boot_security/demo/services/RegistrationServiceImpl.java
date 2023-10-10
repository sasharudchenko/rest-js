package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    RoleRepository roleRepository;
    @Autowired
    public RegistrationServiceImpl(PasswordEncoder passwordEncoder,
                                   UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role defRole = roleRepository.findByName("ROLE_USER");
        List<Role> roles = List.of(defRole);
        user.setRoles(roles);

        userRepository.save(user);
    }
}
