package com.helpHands.helpHands.authentication.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration.hours}")
    private int expirationHours;

    /**
     * Gera um token JWT para o usuário autenticado.
     * O "subject" do token é o username (geralmente o email).
     */
    public String generateToken(UserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(now())
                    .withExpiresAt(expiration())
                    .withSubject(user.getUsername())  // ✅ ADAPTE: pode adicionar claims extras aqui
                    // Exemplo de claim extra: .withClaim("userId", user.getUser().getId())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Erro ao gerar token JWT.", e);
        }
    }

    /**
     * Valida o token e retorna o subject (username/email do usuário).
     * Lança exceção se o token for inválido ou expirado.
     */
    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    // ✅ ADAPTE: ajuste o fuso horário para o seu (ex: "America/Sao_Paulo")
    private Instant now() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expiration() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .plusHours(expirationHours)
                .toInstant();
    }
}
