package com.acme.learning.platform.iam.application.internal.outboundservices.tokens;

import org.springframework.security.core.Authentication;

/**
 * TokenService interface
 * This interface is used to generate and validate tokens
 */
public interface TokenService {
    /**
     * Generate a token from an authentication object
     * @param authentication the authentication object
     * @return String the generated token
     *
     */
    String generateToken(Authentication authentication);

    /**
     * Extract the username from a token
     * @param token the token
     * @return String the username
     */
    String getUsernameFromToken(String token);

    /**
     * Validate a token
     * @param token the token
     * @return boolean true if the token is valid, false otherwise
     */
    boolean validateToken(String token);
}
