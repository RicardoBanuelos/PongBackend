package com.pong.controllers;

import com.pong.config.UserAuthProvider;
import com.pong.dtos.CredentialsDto;
import com.pong.dtos.SignUpDto;
import com.pong.dtos.UserDto;
import com.pong.exceptions.AppException;
import com.pong.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/user/login")
    public ResponseEntity<UserDto> login(
            @Valid @RequestBody CredentialsDto cd
    ) {
        UserDto user = userService.login(cd);
        user.setToken(userAuthProvider.createToken(user));

        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody SignUpDto signUpDto
    ) {
        UserDto userDto = userService.register(signUpDto);
        userDto.setToken(userAuthProvider.createToken(userDto));

        return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto);
    }

    @PostMapping("/user/validate")
    public ResponseEntity<String> validate(@RequestBody String token) {
        if(!userAuthProvider.validateToken(token).isAuthenticated())
            return ResponseEntity.ok("Valid Token");

        throw new AppException("Invalid Token", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getById(
        @PathVariable Long id
    ) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        HashMap<String, String> errors = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError)error).getField();
            var errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
