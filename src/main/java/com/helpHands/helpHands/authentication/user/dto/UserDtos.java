package com.helpHands.helpHands.authentication.user.dto;

import com.seuapp.user.entity.Role;
import com.seuapp.user.entity.RoleName;

import java.util.List;

/**
 * DTOs de autenticação e usuário.
 *
 * ✅ ADAPTE: adicione ou remova campos nos Records conforme seu projeto.
 *
 * Exemplos de campos extras para CreateUserDto:
 *   String name, String phone, LocalDate birthDate
 */
public class UserDtos {

    /**
     * Dados recebidos no login.
     * ✅ ADAPTE: se usar username em vez de email, troque o campo.
     */
    public record LoginUserDto(
        String email,
        String password
    ) {}

    /**
     * Dados recebidos para criar um novo usuário.
     * ✅ ADAPTE: adicione campos extras do seu usuário.
     */
    public record CreateUserDto(
        String email,
        String password,
        RoleName role
        // Adicione outros campos aqui, ex:
        // String name,
        // String phone
    ) {}

    /**
     * Resposta após o login bem-sucedido (contém o token JWT).
     */
    public record LoginResponseDto(
        String token
    ) {}

    /**
     * Dados retornados ao consultar um usuário.
     * ✅ ADAPTE: inclua os campos que fazem sentido expor.
     */
    public record UserResponseDto(
        Long id,
        String email,
        List<Role> roles
        // Adicione outros campos aqui, ex:
        // String name
    ) {}
}
