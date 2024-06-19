package org.example.jwtauthpractice.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.auth.presentation.dto.request.LoginRequest;
import org.example.jwtauthpractice.domain.auth.presentation.dto.request.SignupRequest;
import org.example.jwtauthpractice.domain.auth.presentation.dto.response.TokenResponse;
import org.example.jwtauthpractice.domain.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
