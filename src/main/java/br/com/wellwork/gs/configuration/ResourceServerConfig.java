package br.com.wellwork.gs.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    @ConditionalOnProperty(prefix = "security", name = "enabled", havingValue = "false")
    SecurityFilterChain securityFilterChainLocal(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests.anyRequest().permitAll()
        ).build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "security", name = "enabled", havingValue = "true")
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/usuario/**")
                                .hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/**")
                                .hasRole("ADMIN")

                                .anyRequest()
                                .denyAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new CompositeGrantedAuthoritiesConverter());
        return jwtConverter;
    }
}
