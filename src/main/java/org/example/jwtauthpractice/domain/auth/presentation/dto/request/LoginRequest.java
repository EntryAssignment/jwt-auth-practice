package org.example.jwtauthpractice.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
