package com.example.server.service;

import com.example.server.exception.UserDataAccessException;
import com.example.server.exception.UserNotFoundException;
import com.example.server.mapper.DtoMapper;
import com.example.server.model.User;
import com.example.server.model.order.dto.UserResponseDto;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for handling user details and authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, DtoMapper dtoMapper) {
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
    }

    /**
     * Load user details by email for authentication.
     *
     * @param email The email of the user.
     * @return UserDetails object containing user information.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    /**
     * Get a user by email.
     *
     * @param userEmail The email of the user.
     * @return The User object.
     * @throws UserNotFoundException If the user is not found.
     */
    public User getUser(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User cant find!"));
    }

    /**
     * Get a list of all users with their details.
     *
     * @return List of UserResponseDto objects.
     * @throws UserDataAccessException If there is an error accessing user data.
     */
    public List<UserResponseDto> getAllUsers() {
        try {
            List<User> userList = userRepository.findAll();
            return dtoMapper.mapUserResponseDtoList(userList);
        } catch (Error e) {
            e.printStackTrace();
            throw new UserDataAccessException("An error occurred while accessing user data. The requested user data could not be retrieved.");
        }

    }
}
