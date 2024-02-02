package com.example.server.configuration;

import com.example.server.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * Configuration class for security settings and beans.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final RSAKeyProperties keyProperties;

    /**
     * Constructor to initialize the security configuration with RSA key properties.
     *
     * @param keyProperties RSA key properties.
     */
    public SecurityConfiguration(RSAKeyProperties keyProperties) {
        this.keyProperties = keyProperties;
    }

    /**
     * Creates a BCrypt password encoder bean.
     *
     * @return BCryptPasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an authentication manager bean.
     *
     * @param userDetailsService User details service.
     * @return AuthenticationManager bean.
     */
    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    /**
     * Creates a custom JWT granted authorities converter bean.
     *
     * @return CustomJwtGrantedAuthoritiesConverter bean.
     */
    @Bean
    public CustomJwtGrantedAuthoritiesConverter customJwtGrantedAuthoritiesConverter() {
        return new CustomJwtGrantedAuthoritiesConverter();
    }

    /**
     * Creates a JWT authentication converter bean.
     *
     * @param customJwtConverter Custom JWT converter.
     * @return JwtAuthenticationConverter bean.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(CustomJwtGrantedAuthoritiesConverter customJwtConverter) {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(customJwtConverter);
        return converter;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http HttpSecurity instance.
     * @return SecurityFilterChain bean.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("*"));
                    configuration.setAllowedMethods(List.of("*"));
                    configuration.setAllowedHeaders(List.of("*"));
                    return configuration;
                }))
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(new AntPathRequestMatcher("/")).permitAll();
                    authorize.requestMatchers(new AntPathRequestMatcher("/product/get")).permitAll();
                    authorize.requestMatchers(new AntPathRequestMatcher("/orders/create")).permitAll();
                    authorize.requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll();
                    authorize.requestMatchers("/orders/get").hasAuthority("ADMIN");
                    authorize.requestMatchers("/user/get").hasAuthority("ADMIN");
                    authorize.anyRequest().authenticated();
                })
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter(customJwtGrantedAuthoritiesConverter()))
                        )
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * Creates a JWT decoder bean using NimbusJwtDecoder.
     *
     * @return NimbusJwtDecoder bean.
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyProperties.getPublicKey()).build();
    }

    /**
     * Creates a JWT encoder bean using NimbusJwtEncoder.
     *
     * @return NimbusJwtEncoder bean.
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(keyProperties.getPublicKey()).privateKey(keyProperties.getPrivateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);

    }
}