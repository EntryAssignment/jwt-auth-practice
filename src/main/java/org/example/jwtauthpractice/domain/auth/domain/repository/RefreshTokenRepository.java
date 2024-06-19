package org.example.jwtauthpractice.domain.auth.domain.repository;

import org.example.jwtauthpractice.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
