package com.pong.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pong.dtos.UserDto;
import com.pong.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    private final UserService userService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto userDto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_00);

        return JWT.create()
                .withIssuer(userDto.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstname", userDto.getFirstname())
                .withClaim("lastname", userDto.getLastname())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        UserDto userDto = UserDto.builder()
                .username(decoded.getIssuer())
                .firstname(decoded.getClaim("firstname").asString())
                .lastname(decoded.getClaim("lastname").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        String issuer = decoded.getIssuer();
        UserDto userDto = userService.findByUsername(issuer);

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }
}
