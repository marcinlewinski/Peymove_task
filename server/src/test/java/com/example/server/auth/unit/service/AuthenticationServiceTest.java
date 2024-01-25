package com.example.server.auth.unit.service;

import com.example.server.exception.AuthenticationFailedException;
import com.example.server.model.LoginResponseDTO;
import com.example.server.service.AuthenticationService;
import com.example.server.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void testLoginUser_ValidCredentials_ShouldReturnJwtToken() {
        String email = "user@example.com";
        String password = "password";
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);
        when(tokenService.generateJwt(authentication))
                .thenReturn("mocked-jwt-token");

        LoginResponseDTO response = authenticationService.loginUser(email, password);

        assertEquals("mocked-jwt-token", response.getJwt());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(tokenService, times(1)).generateJwt(authentication);
    }

    @Test
    void testLoginUser_InvalidCredentials_ShouldThrowException() {
        String email = "user@example.com";
        String password = "wrong-password";
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        AuthenticationFailedException exception = assertThrows(AuthenticationFailedException.class,
                () -> authenticationService.loginUser(email, password));
        assertEquals("Authentication failed", exception.getMessage());
        verify(authenticationManager, times(1)).authenticate(any());
    }
}