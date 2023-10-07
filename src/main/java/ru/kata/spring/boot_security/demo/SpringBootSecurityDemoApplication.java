package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	@Autowired
	private static RoleRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
//		Role user = new Role("User");
//		Role admin = new Role("Admin");
//		Role customer = new Role("Customer");
//
//		repo.saveAll(List.of(user, admin, customer));
//
//		List<Role> listRoles = repo.findAll();


	}

}

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class RoleRepositoryTests {

	@Autowired private RoleRepository repo;

	@Test
	public void testCreateRoles() {
		Role user = new Role("User");
		Role admin = new Role("Admin");
		Role customer = new Role("Customer");

		repo.saveAll(List.of(user, admin, customer));

		List<Role> listRoles = repo.findAll();

		assertThat(listRoles.size()).isEqualTo(3);
	}

}
