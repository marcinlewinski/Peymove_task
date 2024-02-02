package com.example.server.service;

import com.example.server.exception.AuthenticationFailedException;
import com.example.server.exception.RegistrationException;
import com.example.server.model.LoginResponseDTO;
import com.example.server.model.Role;
import com.example.server.model.User;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service for user authentication and registration.
 */
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates a user with the provided email and password and generates a JWT token.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return LoginResponseDTO containing the JWT token.
     * @throws AuthenticationFailedException if authentication fails.
     */
    public LoginResponseDTO loginUser(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String token = tokenService.generateJwt(authentication);
            return new LoginResponseDTO(token);
        } catch (AuthenticationException exception) {
            exception.printStackTrace();
            throw new AuthenticationFailedException("Authentication failed");
        }
    }

    /**
     * Registers a new user with the provided email, password, and username.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @param username The username of the user.
     * @throws RegistrationException if registration fails.
     */
    public void registerUser(String email, String password, String username) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RegistrationException("User with this email already exists.");
        }

        try {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setAuthorities(getDefaultRoles());

            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RegistrationException("Registration failed. User with this email already exists.");
        } catch (Exception e) {
            throw new RegistrationException("Registration failed due to an unexpected error.");
        }
    }

    private Set<Role> getDefaultRoles() {
        Optional<Role> userRole = roleRepository.findByAuthority("USER");

        Set<Role> authorities = new HashSet<>();
        Role role = userRole.orElseThrow(() -> new IllegalStateException("Default role 'ROLE_USER' not found in the database."));
        authorities.add(role);

        return authorities;
    }
}
