package com.helpHands.helpHands.service;

import com.helpHands.helpHands.adapters.UserDetailsImpl;
import com.helpHands.helpHands.config.SecurityConfiguration;
import com.helpHands.helpHands.domain.authentication.Role;
import com.helpHands.helpHands.domain.authentication.User;
import com.helpHands.helpHands.dto.userAuthentication.CreateUserDto;
import com.helpHands.helpHands.dto.userAuthentication.LoginUserDto;
import com.helpHands.helpHands.dto.userAuthentication.RecoveryJwtTokenDto;
import com.helpHands.helpHands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }


    public void createUser(CreateUserDto createUserDto) {

        User newUser = User.builder()
                .email(createUserDto.email())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        userRepository.save(newUser);
    }
}
