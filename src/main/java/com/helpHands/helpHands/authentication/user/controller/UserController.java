package com.helpHands.helpHands.authentication.user.controller;

import com.helpHands.helpHands.authentication.user.dto.UserDtos;
import com.helpHands.helpHands.authentication.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDtos.LoginResponseDto> login(@RequestBody UserDtos.LoginUserDto dto) {
        UserDtos.LoginResponseDto response = userService.authenticateUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDtos.CreateUserDto dto) {
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profile() {
        return ResponseEntity.ok("Usuário autenticado com sucesso!");
    }

}
