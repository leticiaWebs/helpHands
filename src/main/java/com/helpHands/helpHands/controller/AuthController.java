package com.helpHands.helpHands.controller;

import com.helpHands.helpHands.dto.LoginRequest;
import com.helpHands.helpHands.dto.LoginResponse;
import com.helpHands.helpHands.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // ⚠️ Em produção: buscar usuário do banco e validar senha com BCrypt
        if ("admin".equals(request.username()) && "123456".equals(request.password())) {
            String token = jwtService.generateToken(request.username());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (jwtService.isTokenValid(token)) {
            String username = jwtService.extractUsername(token);
            return ResponseEntity.ok("Usuário autenticado: " + username);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
