package org.example.user.controller;

import org.example.security.JwtService;
import org.example.user.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(AuthenticationManager authManager, JwtService jwt) {
        this.authManager = authManager; this.jwt = jwt;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequest req) {
        var auth = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
        Authentication result = authManager.authenticate(auth); // throws if bad
        String token = jwt.generate(result.getName());
        return Map.of("token", token, "tokenType", "Bearer");
    }
}

//record LoginRequest(String email, String password) {}
