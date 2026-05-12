package com.helpHands.helpHands.authentication.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exemplo de controller protegido por ROLE_ADMIN.
 *
 * ✅ ADAPTE: renomeie, mova, ou transforme em um controller real do seu projeto.
 *    Não se esqueça de mapear o path "/admin/**" em SecurityConfiguration.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("Administrador autenticado com sucesso!");
    }

    // ✅ ADAPTE: adicione seus endpoints administrativos aqui
}
