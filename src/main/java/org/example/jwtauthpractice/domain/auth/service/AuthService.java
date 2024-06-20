package org.example.jwtauthpractice.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.auth.exception.PasswordMismatchException;
import org.example.jwtauthpractice.domain.auth.presentation.dto.request.LoginRequest;
import org.example.jwtauthpractice.domain.auth.presentation.dto.request.SignupRequest;
import org.example.jwtauthpractice.domain.auth.presentation.dto.response.TokenResponse;
import org.example.jwtauthpractice.domain.user.domain.User;
import org.example.jwtauthpractice.domain.user.domain.repository.UserRepository;
import org.example.jwtauthpractice.domain.user.facade.UserFacade;
import org.example.jwtauthpractice.global.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserFacade userFacade;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        userRepository.save(User.builder().username(signupRequest.getUsername()).password(passwordEncoder.encode(signupRequest.getPassword())).build());
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {
        User user = userFacade.findByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(request.getUsername());
    }
}
