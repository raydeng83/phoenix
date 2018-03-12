package com.ldeng.backend;

import com.ldeng.backend.model.Role;
import com.ldeng.backend.model.User;
import com.ldeng.backend.model.UserRole;
import com.ldeng.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Admin");
		user1.setLastName("Admin");
		user1.setUsername("Admin");
		user1.setPassword("password");
		user1.setEmail("Admin@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoles);

		userRoles.clear();
		User user2 = new User();
		user2.setFirstName("Ben");
		user2.setLastName("Franklin");
		user2.setUsername("BFranklin");
		user2.setPassword("password");
		user2.setEmail("BFranklin@gmail.com");
		Role role2 = new Role();
		role2.setRoleId(1);
		role2.setName("ROLE_USER");
		userRoles.add(new UserRole(user2, role2));
		userService.createUser(user2, userRoles);

	}
}
