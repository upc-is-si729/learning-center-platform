package com.acme.learning.platform.iam.application.internal.outboundservices.hashing;

public interface HashingService {
    String encode(CharSequence rawPassword);

}
