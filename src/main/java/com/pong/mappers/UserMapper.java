package com.pong.mappers;

import com.pong.dtos.SignUpDto;
import com.pong.dtos.UserDto;
import com.pong.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    @Mapping(target = "password", ignore = true)
    User signUp(SignUpDto signUpDto);
}
