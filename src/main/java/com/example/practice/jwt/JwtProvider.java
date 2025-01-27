package com.example.practice.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    // 예시를 위해 하드코딩한 시크릿 키 (실제 운영 환경에서는 안전하게 관리해야 함)
    private static final String SECRET_KEY = "YourJWTSecretKey-PleaseChangeThisToARealSecretKey123";
    private static final long EXPIRATION_MS = 1000 * 60 * 60; // 토큰 유효기간(1시간)

    private final Key key;

    public JwtProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * JWT 토큰 생성
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(username)                       // 토큰의 주체(사용자명)
                .setIssuedAt(now)                           // 토큰 발급 시간
                .setExpiration(expiryDate)                  // 토큰 만료 시간
                .signWith(key, SignatureAlgorithm.HS256)    // 서명
                .compact();
    }

    /**
     * 토큰에서 사용자명 추출
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 토큰 유효성 검사
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {
            log.info("Invalid Token");
        }
        return false;
    }
}
