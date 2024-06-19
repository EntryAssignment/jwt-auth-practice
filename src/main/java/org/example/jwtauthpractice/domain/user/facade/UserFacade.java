package org.example.jwtauthpractice.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.user.domain.repository.UserRepository;
import org.example.jwtauthpractice.domain.user.exception.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public org.example.jwtauthpractice.domain.user.domain.User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public org.example.jwtauthpractice.domain.user.domain.User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}