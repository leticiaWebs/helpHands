package com.helpHands.helpHands.authentication.security;

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
                // Valida o token e obtém o subject (email/username)
                String subject = jwtTokenService.getSubjectFromToken(token);

                // Busca o usuário no banco
                User user = userRepository.findByEmail(subject)  // ✅ ADAPTE: findByUsername se necessário
                        .orElseThrow(() -> new RuntimeException("Usuário do token não encontrado."));

                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                // Registra a autenticação no contexto do Spring Security
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                /*
                 * ✅ ADAPTE: você pode customizar a resposta de erro aqui.
                 *
                 * Opção 1 (padrão atual): lança exceção → Spring retorna 500
                 *
                 * Opção 2 (mais RESTful): retorna 401 com JSON:
                 *   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 *   response.setContentType("application/json");
                 *   response.getWriter().write("{\"error\": \"Token ausente ou inválido.\"}");
                 *   return;
                 */
                throw new RuntimeException("Token JWT ausente no cabeçalho Authorization.");
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extrai o token JWT do cabeçalho "Authorization: Bearer <token>"
     */
    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

    /**
     * Retorna true se o endpoint exige autenticação (não está na lista pública).
     */
    private boolean isEndpointRestricted(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_PUBLIC).contains(uri);
    }

}
