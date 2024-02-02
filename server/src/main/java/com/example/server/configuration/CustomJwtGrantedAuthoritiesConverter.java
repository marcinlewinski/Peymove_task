package com.example.server.configuration;

import com.example.server.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Custom converter to extract authorities from JWT claims and convert them into GrantedAuthorities.
 */
public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    /**
     * Converts JWT claims to a collection of GrantedAuthorities.
     *
     * @param jwt The JWT containing claims.
     * @return Collection of GrantedAuthorities extracted from the JWT claims.
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // Extracting authorities claim from the JWT
        String authoritiesClaim = jwt.getClaim("authorities");

        // Mapping and collecting authorities into a list of GrantedAuthorities
        return Arrays.stream(authoritiesClaim.split(","))
                .map(String::trim)
                .map(Role::new)
                .collect(Collectors.toList());

    }
}
