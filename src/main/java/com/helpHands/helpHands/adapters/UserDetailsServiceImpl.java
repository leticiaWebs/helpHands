package com.helpHands.helpHands.adapters;

import com.helpHands.helpHands.domain.authentication.UserAuthentication;
import com.helpHands.helpHands.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserAuthenticationRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) {
        UserAuthentication userAuthentication = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(
                userAuthentication.getEmail(),
                userAuthentication.getSenha(),
                List.of(new SimpleGrantedAuthority(userAuthentication.getRole()))
        );

    }
}
