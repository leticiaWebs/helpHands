package com.helpHands.helpHands.domain.authentication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserAuthentication {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String senha;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserAuthentication(Long id, String senha, String email, String role) {
        this.id = id;
        this.senha = senha;
        this.email = email;
        this.role = role;
    }

}