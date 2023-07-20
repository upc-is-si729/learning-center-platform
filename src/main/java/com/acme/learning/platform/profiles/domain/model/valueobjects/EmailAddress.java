package com.acme.learning.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmailAddress(String address) {
    public EmailAddress() {
        this(null);
    }
}
