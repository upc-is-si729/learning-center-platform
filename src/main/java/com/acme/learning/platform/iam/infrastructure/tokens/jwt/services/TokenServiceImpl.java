package com.acme.learning.platform.iam.infrastructure.tokens.jwt.services;

import com.acme.learning.platform.iam.application.internal.outboundservices.tokens.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

/**
 * This class is responsible for generating and validating JWT tokens.
 * It uses the secret and expiration days from the application.properties file.

 */
@Service
public class TokenServiceImpl implements TokenService {
    private final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    /**
     * @InheritDoc
     */
    @Override
    public String generateToken(Authentication authentication) {
        String subject = authentication.getName();
        var issuedAt = new Date();
        var expiration = DateUtils.addDays(issuedAt, expirationDays);
        var key = getSigningKey();
        return Jwts.builder()
                .subject(subject)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * @InheritDoc
     */
    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * @InheritDoc
     */
    @Override
    public boolean validateToken(String token) {
        return false;
    }

    /**
     * Extract a claim from a token
     * @param token the token
     * @param claimsResolvers the claims resolver
     * @param <T> the type of the claim
     * @return T the claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Extract all claims from a token
     * @param token the token
     * @return Claims the claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    /**
     * Get the signing key
     * @return SecretKey the signing key
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
