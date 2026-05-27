package com.example.autodeploy_tracker.security;

import com.example.autodeploy_tracker.model.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "myverysecuresecretkeymyverysecuresecretkey123456";

    public String generateToken(
            String username,
            Role role) {

        return Jwts.builder()

                .setSubject(username)

                .claim("role", role.name())

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )

                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY
                )

                .compact();
    }

    public String extractUsername(
            String token) {

        Claims claims = Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(token)

                .getBody();

        return claims.getSubject();
    }

    public String extractRole(
            String token) {

        Claims claims = Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(token)

                .getBody();

        return claims.get(
                "role",
                String.class
        );
    }

    public boolean validateToken(
            String token,
            String username) {

        String extractedUsername =
                extractUsername(token);

        return extractedUsername.equals(username);
    }
}