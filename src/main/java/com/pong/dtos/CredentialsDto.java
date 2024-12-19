package com.pong.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CredentialsDto (
        @NotEmpty
        String username,
        @NotEmpty
        char[] password
) {
}
