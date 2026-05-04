package com.helpHands.helpHands.dto.userAuthentication;

import com.helpHands.helpHands.domain.authentication.RoleName;

public record CreateUserDto(
        String email,
        String password,
        RoleName role

) {
}
