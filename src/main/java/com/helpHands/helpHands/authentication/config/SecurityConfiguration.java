package com.helpHands.helpHands.authentication.config;

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

    // ✅ ADAPTE: endpoints que NÃO precisam de autenticação
    public static final String[] ENDPOINTS_PUBLIC = {
            "/users/login",  // login
            "/users",        // criar conta
            // Adicione outros endpoints públicos aqui, ex:
            // "/products",
            // "/h2-console/**",  // apenas para dev com H2
    };

    // ✅ ADAPTE: endpoints que exigem apenas estar autenticado (qualquer role)
    public static final String[] ENDPOINTS_AUTHENTICATED = {
            "/users/profile",
            // Adicione seus endpoints autenticados aqui
    };

    // ✅ ADAPTE: endpoints exclusivos para ROLE_ADMIN
    public static final String[] ENDPOINTS_ADMIN = {
            "/admin/**",
            // Adicione seus endpoints de admin aqui
    };

    // ✅ ADAPTE: endpoints exclusivos para ROLE_USER
    public static final String[] ENDPOINTS_USER = {
            "/users/dashboard",
            // Adicione seus endpoints de usuário comum aqui
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // Desativa CSRF (padrão para APIs REST)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sem sessão (stateless)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(ENDPOINTS_PUBLIC).permitAll()
                                .requestMatchers(ENDPOINTS_AUTHENTICATED).authenticated()
                                .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")
                                .requestMatchers(ENDPOINTS_USER).hasRole("USER")
                                // ✅ ADAPTE: escolha entre denyAll() ou authenticated() para o restante
                                .anyRequest().denyAll()  // Bloqueia tudo que não foi mapeado
                        // .anyRequest().authenticated()  // Alternativa: exige login para o resto
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
