package com.acme.learning.platform.iam.infrastructure.hashing.bcrypt.services;

import com.acme.learning.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImpl implements HashingService {
    private final BCryptPasswordEncoder passwordEncoder;

    HashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
