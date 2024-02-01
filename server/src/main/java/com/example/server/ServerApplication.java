package com.example.server;

import com.example.server.initializer.DataInitializer;
import com.example.server.model.Role;
import com.example.server.model.User;
import com.example.server.model.order.Product;
import com.example.server.repository.ProductRepository;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder, DataInitializer dataInitializer
    ) {
		return args -> {
			if (repository.findByAuthority("ADMIN").isPresent()) return;
			try {
				Role adminRole = repository.save(new Role("ADMIN"));
				repository.save(new Role("USER"));
				Set<Role> roles = new HashSet<>();
				roles.add(adminRole);

				User admin = new User(UUID.randomUUID(),"admin@example.com" ,"admin", passwordEncoder.encode("password"), roles);
				System.out.println(admin);
				userRepository.save(admin);

                dataInitializer.init();

			} catch (Error e) {
				e.printStackTrace();
			}

		};
	}
}
