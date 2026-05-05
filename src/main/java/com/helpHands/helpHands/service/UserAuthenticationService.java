package com.helpHands.helpHands.service;

import com.helpHands.helpHands.domain.authentication.UserAuthentication;
import com.helpHands.helpHands.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAuthenticationService {


    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserAuthentication salvar(UserAuthentication userAuthentication) {
        userAuthentication.setSenha(encoder.encode(userAuthentication.getSenha()));
        return userAuthenticationRepository.save(userAuthentication);
    }
}
