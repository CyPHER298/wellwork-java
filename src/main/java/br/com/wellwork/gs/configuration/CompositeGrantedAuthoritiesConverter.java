package br.com.wellwork.gs.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.stream.Collectors;

public class CompositeGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String ROLES_CLAIM = "scp"; // Claim where both roles and scopes are located
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String SCOPE_PREFIX = "SCOPE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // 1. Get the list of claims from the JWT (assumed to be stored in "scp")
        Collection<String> claims = jwt.getClaimAsStringList(ROLES_CLAIM);

        if (claims == null) {
            return java.util.Collections.emptyList();
        }

        // 2. Iterate and apply the correct prefix logic
        return claims.stream()
                .map(claim -> {
                    if (claim.startsWith(ROLE_PREFIX)) {
                        // Item already has ROLE_ prefix (e.g., "ROLE_USER"), use as is
                        return new SimpleGrantedAuthority(claim);
                    } else {
                        // Item is a scope (e.g., "fiap.read"), apply SCOPE_ prefix
                        return new SimpleGrantedAuthority(SCOPE_PREFIX + claim);
                    }
                })
                .collect(Collectors.toList());
    }
}
