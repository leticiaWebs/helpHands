package com.helpHands.helpHands.authentication.user.repository;

import com.seuapp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório JPA para a entidade User.
 *
 * ✅ ADAPTE: se o seu campo de login não for "email", renomeie o método abaixo.
 * Exemplo com username:
 *   Optional<User> findByUsername(String username);
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
