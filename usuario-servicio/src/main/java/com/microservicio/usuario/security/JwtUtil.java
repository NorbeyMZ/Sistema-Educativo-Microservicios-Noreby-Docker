package com.microservicio.usuario.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "686D795365637265744B657958795365637265744B6579";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String extractUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    public static String extractRole(String token) {
        return getAllClaims(token).get("role", String.class);
    }

    private static Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
