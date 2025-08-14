package com.example.kent_notifier.app.Security.JWT;

import com.example.kent_notifier.app.User.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("{kent-notifier.login.jwtSecret}")
    private String jwtSecret;
    
    @Value("{kent-notifier.login.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        
        String jwt = Jwts.builder()
                .subject(userPrincipal.getEmailAddress())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getKey())
                .compact();
        
        return jwt;
    }
    
    private SecretKey getKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    } 
    
    // grab the principals email from the JWT token to verify the user.
    public String getEmailFromJwt(String jwt) {
        String email = Jwts.parser()
                        .verifyWith(getKey()).build()
                        .parseSignedClaims(jwt)
                        .getPayload()
                        .getSubject();
        
        return email;
    }

    public Date getExpirationTimeFromJwt(String jwt) {
        Date time = Jwts.parser()
                        .verifyWith(getKey()).build()
                        .parseSignedClaims(jwt)
                        .getPayload()
                        .getExpiration();

        return time;
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(getKey()).build().parse(token);
            return true; 
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage()); 
        }

        return false;
    }

}
