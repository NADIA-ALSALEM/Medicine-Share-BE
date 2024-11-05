package com.medicine.donate.medicine.util;

import com.medicine.donate.medicine.config.PropertiesConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final PropertiesConfig propertiesConfig;

    public String generateToken(String uuid) {
        return Jwts.builder()
                .setSubject(uuid)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + propertiesConfig.getExpiryTime()))
                .signWith(SignatureAlgorithm.HS256, propertiesConfig.getSecret())
                .compact();
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(propertiesConfig.getSecret())
                .parseClaimsJws(token).getBody().getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(propertiesConfig.getSecret())
                .parseClaimsJws(token).getBody().getExpiration();
    }
}