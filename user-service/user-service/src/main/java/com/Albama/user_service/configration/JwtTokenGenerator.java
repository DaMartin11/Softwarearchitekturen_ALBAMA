package com.Albama.user_service.configration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtTokenGenerator {
    public static void main(String[] args) {
        String secret = "your-256-bit-secret"; // Ensure this matches your application properties
        byte[] keyBytes = secret.getBytes();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // 1 day validity

        String jwtToken = Jwts.builder()
                .setSubject("username")
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
                .compact();

        System.out.println("Bearer " + jwtToken);
    }
}
