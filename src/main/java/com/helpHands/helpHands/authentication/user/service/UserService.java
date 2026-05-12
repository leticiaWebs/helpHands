package com.helpHands.helpHands.authentication.user.service;

import com.seuapp.config.SecurityConfiguration;
import com.seuapp.security.JwtTokenService;
import com.seuapp.security.UserDetailsImpl;
import com.seuapp.user.dto.UserDtos.*;
import com.seuapp.user.entity.Role;
import com.seuapp.user.entity.User;
import com.seuapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service com a lógica de autenticação e criação de usuários.
 *
 * ✅ ADAPTE: adicione validações extras, regras de negócio, etc.
 */
@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    /**
     * Autentica o usuário e retorna um token JWT.
     *
     * @param dto Contém email e senha
     * @return DTO com o token gerado
     * @throws org.springframework.security.core.AuthenticationException se as credenciais forem inválidas
     */
    public LoginResponseDto authenticateUser(LoginUserDto dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication authentication = authenticationManager.authenticate(authToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);

        return new LoginResponseDto(token);
    }

    /**
     * Cria um novo usuário com senha criptografada.
     *
     * ✅ ADAPTE: adicione aqui validações de negócio, como:
     *   - verificar se o email já existe
     *   - enviar email de boas-vindas
     *   - criar entidades relacionadas
     *
     * @param dto Dados do novo usuário
     */
    public void createUser(CreateUserDto dto) {
        // ✅ ADAPTE: adicione mais campos ao builder conforme sua entidade User
        User newUser = User.builder()
                .email(dto.email())
                .password(securityConfiguration.passwordEncoder().encode(dto.password()))
                .roles(List.of(Role.builder().name(dto.role()).build()))
                // .name(dto.name())  // Exemplo de campo extra
                .build();

        userRepository.save(newUser);
    }
}
