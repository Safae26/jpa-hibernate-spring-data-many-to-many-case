package net.safae.jpa_many_to_many_case;

import net.safae.jpa_many_to_many_case.entities.Role;
import net.safae.jpa_many_to_many_case.entities.User;
import net.safae.jpa_many_to_many_case.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaManyToManyCaseApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpaManyToManyCaseApplication.class, args);
	}
	@Bean
	CommandLineRunner start(UserService userService) {
		return args -> {

			User u = new User();
			u.setUsername("admin");
			u.setPassword("123456");
			userService.addNewUser(u);

			User u1 = new User();
			u1.setUsername("safae");
			u1.setPassword("safae");
			userService.addNewUser(u1);

			Stream.of("STUDENT", "USER", "ADMIN").forEach(r->{
				Role role = new Role();
				role.setRoleName(r);
				userService.addNewRole(role);
			});

			userService.addRoleToUser("safae", "STUDENT");
			userService.addRoleToUser("safae", "USER");
			userService.addRoleToUser("admin", "USER");
			userService.addRoleToUser("admin", "ADMIN");

			try {
				User user = userService.authenticate("safae", "safae");
				System.out.println(user.getUserId());
				System.out.println(user.getUsername());
				System.out.println("Roles =>");
				user.getRoles().forEach(role->{
					System.out.println("Role => "+role);
				});
			} catch(Exception exception) {
				exception.printStackTrace();
			}

		};
	}

}
