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

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);


	}
//	внедрения делай через конструктор +
//
//	замени EAGER на LAZY+
//
//	в service добавь интерфейсы +
//
//	при обновлении пользователя нет возможности изменить роль +
//
//	данные под колонками сдвинуты ???


//	User: username должен быть уникальным (логин, возвращаемое значение getUsername):
//	необходимо сделать их уникальными на уровне БД, затем добавить валидацию, чтоб при попытке
//	добавить пользователя с уже существующим именем выходило предупреждение, что такой логин уже
//			существует; +++
//
//	RegistrationServiceImpl: поле roleRepository также должен быть приватным; +++
//
//	Классы сервисов: Методы сервиса осуществляющие чтение-запись к БД помечаются @Transactional,
//	делающие только чтение к БД либо не помечаются вовсе либо помечаются также транзакционностью
//	с параметром "только для чтения"; +++
//
//	Контроллеры: внедряй через объявления интерфейса сервисов, а не реализации, работай на
//	абстрактном уровне; +++
//
//	Работа приложения: /user - по этому адресу должна быть отображена информация о текущем пользователе,
//	не /user/{id}; +++
//
///admin/user/1 - в адресе /user/ - лишний, перенаправление и вся остальная работа должна быть
//	по адресу /admin, операции по созданию нового пользователя на странице админа отсутствует;

}
