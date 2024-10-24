package com.pong.controllers;

import com.pong.config.UserAuthProvider;
import com.pong.dtos.CredentialsDto;
import com.pong.dtos.SignUpDto;
import com.pong.dtos.UserDto;
import com.pong.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto cd) {
        UserDto user = userService.login(cd);
        user.setToken(userAuthProvider.createToken(user));

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.register(signUpDto);
        userDto.setToken(userAuthProvider.createToken(userDto));

        return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto);
    }
}
