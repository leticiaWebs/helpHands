package com.helpHands.helpHands.authentication.user.controller;

import com.seuapp.user.dto.UserDtos.*;
import com.seuapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller de autenticação e gerenciamento de usuários.
 *
 * ✅ ADAPTE: renomeie os endpoints, adicione novos, ou remova os de teste.
 *
 * Endpoints criados:
 *  POST /users/login    → realiza o login e retorna o token JWT
 *  POST /users          → cria um novo usuário
 *  GET  /users/profile  → endpoint de teste para usuário autenticado
 *  GET  /admin/test     → endpoint de teste para administradores
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Realiza o login e retorna o token JWT.
     * Endpoint público (não exige autenticação).
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto dto) {
        LoginResponseDto response = userService.authenticateUser(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Cria um novo usuário.
     * Endpoint público (não exige autenticação).
     *
     * ✅ ADAPTE: mude para retornar o usuário criado se necessário.
     */
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint de teste: acessível por qualquer usuário autenticado.
     * ✅ ADAPTE: substitua por um endpoint real do seu projeto.
     */
    @GetMapping("/profile")
    public ResponseEntity<String> profile() {
        return ResponseEntity.ok("Usuário autenticado com sucesso!");
    }

    // ✅ ADAPTE: adicione seus endpoints reais abaixo.
    // Lembre-se de mapeá-los também em SecurityConfiguration.
    //
    // Exemplo:
    // @GetMapping("/{id}")
    // public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
    //     ...
    // }
}
