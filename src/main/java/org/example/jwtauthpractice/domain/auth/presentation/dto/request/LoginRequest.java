package org.example.jwtauthpractice.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
