package com.helpHands.helpHands.authentication.security;

import com.helpHands.helpHands.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ✅ ADAPTE: troque findByEmail por findByUsername se necessário
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new UserDetailsImpl(user);
    }
}
