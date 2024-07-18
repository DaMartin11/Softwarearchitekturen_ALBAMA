package com.Albama.user_service.corn.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJwtService {
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    public String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public String extractUsername(String token);

}
