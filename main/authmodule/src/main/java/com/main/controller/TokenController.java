package com.main.controller;

import java.time.Instant;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.DTO.LoginRequest;
import com.main.DTO.LoginResponse;
import com.main.model.User;
import com.main.repository.UserRepository;

@RestController
public class TokenController {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    
        Optional<User> user = userRepository.findByEmail(loginRequest.username());
        if (user.isEmpty() || !(user.get().isLoginCorrect(loginRequest.password(), bCryptPasswordEncoder) && user.get().isActive())) {
            throw new RuntimeException("Invalid credentials");
        }
        long expirationTime = 3600L; // 1 hour in seconds
        var now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("auth-module")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationTime))
                .subject(user.get().toString())
                .claim("username", user.get().getUsername())
                .claim("email", user.get().getEmail())
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponse(jwtValue, expirationTime));
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
