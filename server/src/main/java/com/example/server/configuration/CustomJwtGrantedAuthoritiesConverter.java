package com.example.server.configuration;

import com.example.server.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        String authoritiesClaim = jwt.getClaim("authorities");

        return Arrays.stream(authoritiesClaim.split(","))
                .map(String::trim)
                .map(Role::new)
                .collect(Collectors.toList());

    }
}
