package com.cwsoft.multi_tenant.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "your_secret_key"; // Use a strong secret
    private final long EXPIRATION_TIME = 86400000; // 1 day

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY);

        return builder.compact();
    }

    public boolean validateToken(String token) {
        // Implement token validation logic
        return true; // Replace with actual validation
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
