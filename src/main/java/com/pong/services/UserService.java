package com.pong.services;

import com.pong.dtos.CredentialsDto;
import com.pong.dtos.SignUpDto;
import com.pong.dtos.UserDto;
import com.pong.entities.User;
import com.pong.exceptions.AppException;
import com.pong.mappers.UserMapper;
import com.pong.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto cd) {
        User user = userRepository.findByUsername(cd.username())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        boolean passwordsMatch = passwordEncoder.matches(CharBuffer.wrap(cd.password()), user.getPassword());
        if(passwordsMatch) {
            return userMapper.toUserDto(user);
        }

        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<User> oUser = userRepository.findByEmail(signUpDto.email());
        if(oUser.isPresent()) {
            throw new AppException("Email already taken", HttpStatus.CONFLICT);
        }

        oUser = userRepository.findByUsername(signUpDto.username());
        if(oUser.isPresent()) {
            throw new AppException("Username already taken", HttpStatus.CONFLICT);
        }

        User user = userMapper.signUp(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);

    }
}
