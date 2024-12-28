package com.example.ecommerceapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {

    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    /**
     * Generates a JWT token for the authenticated user.
     *
     * @param authentication the current authentication object
     * @return a signed JWT token
     */
    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName()) // Typically the username or email
                .setIssuedAt(new Date()) // Token issue date
                .setExpiration(new Date(System.currentTimeMillis() + 846000000)) // Token expiration (10 days)
                .claim("email", authentication.getName()) // Custom claim for email
                .signWith(key) // Signing the token
                .compact();
    }

    /**
     * Extracts the email (or subject) from a JWT token.
     *
     * @param jwt the JWT token
     * @return the email extracted from the token
     */
    public String getEmailFromToken(String jwt) {
        // Strip "Bearer " prefix if present
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        // Parse the claims
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        // Return the email from the claims
        return claims.get("email", String.class);
    }
}
