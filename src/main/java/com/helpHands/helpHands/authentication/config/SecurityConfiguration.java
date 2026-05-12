package com.helpHands.helpHands.authentication.config;

import com.helpHands.helpHands.authentication.security.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] ENDPOINTS_PUBLIC = {
            "/users/login",
            "/users",

    };

    public static final String[] ENDPOINTS_AUTHENTICATED = {
            "/users/profile",

    };


    public static final String[] ENDPOINTS_ADMIN = {
            "/admin/**",

    };


    public static final String[] ENDPOINTS_USER = {
            "/users/dashboard",

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(ENDPOINTS_PUBLIC).permitAll()
                                .requestMatchers(ENDPOINTS_AUTHENTICATED).authenticated()
                                .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")
                                .requestMatchers(ENDPOINTS_USER).hasRole("USER")

                                .anyRequest().denyAll()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
