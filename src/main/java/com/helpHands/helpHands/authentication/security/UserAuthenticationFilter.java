package com.helpHands.helpHands.authentication.security;

import com.helpHands.helpHands.authentication.config.SecurityConfiguration;
import com.helpHands.helpHands.authentication.user.entity.User;
import com.helpHands.helpHands.authentication.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (isEndpointRestricted(request)) {
            String token = extractToken(request);

            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token);

                User user = userRepository.findByEmail(subject)
                        .orElseThrow(() -> new RuntimeException("Usuário do token não encontrado."));

                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                response.getWriter().write("""
                        {
                            "status": 500,
                            "error": "Internal Server Error",
                            "message": "Token JWT ausente no cabeçalho Authorization.",
                            "path": "%s"
                        }
                        """.formatted(request.getRequestURI()));

                throw new RuntimeException("Token JWT ausente no cabeçalho Authorization.");
            }
        }

        filterChain.doFilter(request, response);
    }


    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean isEndpointRestricted(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_PUBLIC).contains(uri);
    }

}
