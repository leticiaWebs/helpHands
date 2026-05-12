package com.helpHands.helpHands.authentication.user.dto;


import com.helpHands.helpHands.authentication.user.entity.Role;
import com.helpHands.helpHands.authentication.user.entity.RoleName;

import java.util.List;

public class UserDtos {


    public record LoginUserDto(
        String email,
        String password
    ) {}


    public record CreateUserDto(
        String email,
        String password,
        RoleName role

    ) {}


    public record LoginResponseDto(
        String token
    ) {}


    public record UserResponseDto(
        Long id,
        String email,
        List<Role> roles

    ) {}
}
