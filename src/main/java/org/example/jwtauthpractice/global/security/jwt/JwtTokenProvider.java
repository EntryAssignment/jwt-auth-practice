package org.example.jwtauthpractice.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.auth.domain.RefreshToken;
import org.example.jwtauthpractice.domain.auth.domain.repository.RefreshTokenRepository;
import org.example.jwtauthpractice.domain.auth.exception.ExpiredTokenException;
import org.example.jwtauthpractice.domain.auth.exception.InvalidTokenException;
import org.example.jwtauthpractice.domain.auth.presentation.dto.response.TokenResponse;
import org.example.jwtauthpractice.domain.user.domain.repository.UserRepository;
import org.example.jwtauthpractice.domain.user.exception.UserNotFoundException;
import org.example.jwtauthpractice.global.security.auth.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String createAccessToken(String accountId) {
        Date now = new Date();

        return Jwts.builder().setSubject(accountId).claim("type", "access").setIssuedAt(now).setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000)).signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret()).compact();
    }

    public String createRefreshToken(String username) {
        Date now = new Date();

        String refreshToken = Jwts.builder().claim("type", "refresh").setIssuedAt(now).setExpiration(new Date(now.getTime() + jwtProperties.getRefreshExpiration() * 1000)).signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret()).compact();

        refreshTokenRepository.save(RefreshToken.builder().username(username).token(refreshToken).build());

        return refreshToken;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String username) {
        Date now = new Date();

        org.example.jwtauthpractice.domain.user.domain.User user = userRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return TokenResponse.builder().accessToken(createAccessToken(username)).refreshToken(createRefreshToken(username)).accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration())).refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration())).build();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix()) && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(7);
        }
        return null;
    }
}