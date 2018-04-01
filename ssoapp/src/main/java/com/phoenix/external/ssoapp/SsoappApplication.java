package com.phoenix.external.ssoapp;

import com.phoenix.external.ssoapp.model.Role;
import com.phoenix.external.ssoapp.model.User;
import com.phoenix.external.ssoapp.model.UserRole;
import com.phoenix.external.ssoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication

public class SsoappApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SsoappApplication.class, args);
	}


}
