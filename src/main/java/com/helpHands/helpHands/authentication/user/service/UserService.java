package com.helpHands.helpHands.authentication.user.service;


import com.helpHands.helpHands.authentication.config.SecurityConfiguration;
import com.helpHands.helpHands.authentication.security.JwtTokenService;
import com.helpHands.helpHands.authentication.security.UserDetailsImpl;
import com.helpHands.helpHands.authentication.user.dto.UserDtos;
import com.helpHands.helpHands.authentication.user.entity.Role;
import com.helpHands.helpHands.authentication.user.entity.User;
import com.helpHands.helpHands.authentication.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserDtos.LoginResponseDto authenticateUser(UserDtos.LoginUserDto dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication authentication = authenticationManager.authenticate(authToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);

        return new UserDtos.LoginResponseDto(token);
    }


    public void createUser(UserDtos.CreateUserDto dto) {
        User newUser = User.builder()
                .email(dto.email())
                .password(securityConfiguration.passwordEncoder().encode(dto.password()))
                .roles(List.of(Role.builder().name(dto.role()).build()))
                .build();

        userRepository.save(newUser);
    }
}
