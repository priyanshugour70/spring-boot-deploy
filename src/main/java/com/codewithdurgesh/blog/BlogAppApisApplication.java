package com.codewithdurgesh.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.entities.Role;
import com.codewithdurgesh.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Running Successfully");

		try {
			// Initialize roles
			Role adminRole = new Role();
			adminRole.setId(AppConstants.ADMIN_USER); // Assuming ADMIN_USER is Integer
			adminRole.setName("ROLE_ADMIN");

			Role normalRole = new Role();
			normalRole.setId(AppConstants.NORMAL_USER); // Assuming NORMAL_USER is Integer
			normalRole.setName("ROLE_USER");

			List<Role> roles = List.of(adminRole, normalRole);
			List<Role> savedRoles = roleRepo.saveAll(roles);

			savedRoles.forEach(role -> {
				System.out.println(role.getName());
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}