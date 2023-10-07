package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.annotations.Test;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	static RoleRepository roleRepo;
	static UserRepository userRepo;
//	@Test
//	public void testAddRoleToNewUser() {
//		Role roleAdmin = roleRepo.findByName("Admin");
//
//		User user = new User("Sasha", "sasha", "rud", 24, "novo");
//
//
//		User savedUser = userRepo.save(user);
//		assertThat(savedUser.getRoles().size()).isEqualTo(1);
//
//	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);


	}

}
