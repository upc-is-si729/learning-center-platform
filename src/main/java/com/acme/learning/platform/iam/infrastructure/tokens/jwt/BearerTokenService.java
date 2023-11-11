package com.acme.learning.platform.iam.infrastructure.tokens.jwt;

import com.acme.learning.platform.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest token);
}
