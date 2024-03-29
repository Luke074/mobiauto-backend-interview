package com.mobiauto.lucas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/auth/usuario/login").permitAll()
                        .requestMatchers("/admin").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO", "ROLE_GERENTE")
                        .requestMatchers(HttpMethod.POST, "/usuarios")
                        .hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO", "ROLE_GERENTE")
                        .requestMatchers(HttpMethod.PUT, "/usuarios").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO")
                        .requestMatchers(HttpMethod.POST, "/veiculos")
                        .hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO")
                        .requestMatchers(HttpMethod.PUT, "/veiculos").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO")
                        .requestMatchers(HttpMethod.DELETE, "/veiculos")
                        .hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO")
                        .requestMatchers("/lojas").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO")
                        .requestMatchers("/revendas").hasAnyAuthority("ROLE_ADMIN", "ROLE_PROPRIETARIO")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
