package com.pong.dtos;

public record SignUpDto (String email,
                         String firstname,
                         String lastname,
                         String username,
                         char[] password) {

}
