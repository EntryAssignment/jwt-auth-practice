package org.example.jwtauthpractice.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.user.domain.repository.UserRepository;
import org.example.jwtauthpractice.domain.user.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        org.example.jwtauthpractice.domain.user.domain.User user = userRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return new CustomUserDetails(user);
    }
}
