package com.microservicio.usuario.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/usuarios/crear").hasRole("ADMIN")
                .requestMatchers("/usuarios/estudiante").hasRole("ESTUDIANTE")
                .requestMatchers("/usuarios/profesor").hasRole("PROFESOR")
                .requestMatchers("/usuarios/todos").hasRole("ADMIN")
                .requestMatchers("/usuarios/admin/**").hasRole("ADMIN")
                .requestMatchers("/usuarios/editar/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf
                .disable()  
            )
            .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    
        return http.build();
    }
    
    
}