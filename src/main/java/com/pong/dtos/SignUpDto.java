package com.pong.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record SignUpDto (
        @NotEmpty
        String email,
        @NotEmpty
        String firstname,
        @NotEmpty
        String lastname,
        @NotEmpty
        String username,
        @NotEmpty
        char[] password) {

}
