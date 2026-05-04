package com.helpHands.helpHands.dto.userAuthentication;

import com.helpHands.helpHands.domain.authentication.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
