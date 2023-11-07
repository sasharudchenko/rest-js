package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //@Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(findByUsername(username));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Такого пользователя не существует");
        }
        return new org.springframework.security.core.userdetails.User
                (user.get().getUsername(), user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }


    @Override
    public User getUser(long id) {
        return userRepository.getById(id);
    }

    @Override

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUser(User user, long id) {
        User userBD = userRepository.getById(id);
        userBD.setName(user.getName());
        userBD.setSurname(user.getSurname());
        userBD.setUsername(user.getUsername());
        userBD.setAge(user.getAge());
        userBD.setCity(user.getCity());
        if (!userBD.getPassword().equals(user.getPassword())) {
            userBD.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userBD.setRoles(user.getRoles());

        userRepository.save(userBD);

    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }


}
