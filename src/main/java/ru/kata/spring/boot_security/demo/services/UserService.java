package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    public User findByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(findByUsername(username));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Такого пользователя не существует");
        }
        return new org.springframework.security.core.userdetails.User
                (user.get().getUsername(),user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()) );
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Transactional
    public User getUser(long id) {
        return userRepository.getById(id);
    }

    @Transactional
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(User user, long id) {
        User userBD = userRepository.getById(id);
        userBD.setName(user.getName());
        userBD.setSurname(user.getSurname());
        userBD.setUsername(user.getUsername());
        userBD.setAge(user.getAge());
        userBD.setCity(user.getCity());
        userBD.setPassword(user.getPassword());

        userRepository.save(userBD);

    }
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getById(Long id) {
        return userRepository.getById(id);
    }



}
