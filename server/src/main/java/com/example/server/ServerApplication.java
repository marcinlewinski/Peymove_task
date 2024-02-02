package com.example.server;

import com.example.server.initializer.DataInitializer;
import com.example.server.model.Role;
import com.example.server.model.User;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    /**
     * A CommandLineRunner bean that initializes roles, a default admin user, and application data.
     *
     * @param repository      Role repository for managing roles.
     * @param userRepository  User repository for managing users.
     * @param passwordEncoder Password encoder for encoding user passwords.
     * @param dataInitializer Data initializer for populating initial data.
     * @return CommandLineRunner bean.
     */
    @Bean
    CommandLineRunner run(RoleRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder, DataInitializer dataInitializer
    ) {
        return args -> {
            // Check if ADMIN role already exists
            if (repository.findByAuthority("ADMIN").isPresent()) return;
            try {
                // Save ADMIN and USER roles
                Role adminRole = repository.save(new Role("ADMIN"));
                repository.save(new Role("USER"));

                // Create a set of roles with ADMIN role
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);

                // Create and save default admin account
                User admin = new User(UUID.randomUUID(), "admin@example.com", "admin", passwordEncoder.encode("password"), roles);
                System.out.println(admin);
                userRepository.save(admin);

                // Initialize products data
                dataInitializer.init();

            } catch (Error e) {
                e.printStackTrace();
            }

        };
    }
}
