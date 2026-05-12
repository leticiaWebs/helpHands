package com.helpHands.helpHands.authentication.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    /** Retorna o objeto User original (útil para acessar dados extras) */
    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * ✅ ADAPTE: se o seu login for por username e não email,
     *    troque para user.getUsername()
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // Mantenha todos como true para um comportamento padrão.
    // ✅ ADAPTE: se precisar bloquear contas, troque a lógica aqui.
    @Override public boolean isAccountNonExpired()    { return true; }
    @Override public boolean isAccountNonLocked()     { return true; }
    @Override public boolean isCredentialsNonExpired(){ return true; }
    @Override public boolean isEnabled()              { return true; }
}
