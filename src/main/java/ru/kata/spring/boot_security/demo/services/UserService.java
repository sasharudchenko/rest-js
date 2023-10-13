package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    void saveUser(User user);
    User getById(Long id);
    void deleteUser(long id);
    void updateUser(User user, long id);
    List<User> allUsers();
    User getUser(long id);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


}
