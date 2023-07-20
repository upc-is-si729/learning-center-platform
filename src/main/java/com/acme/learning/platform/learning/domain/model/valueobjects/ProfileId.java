package com.acme.learning.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProfileId(Long id) {

    public ProfileId {
        if (id < 0) {
            throw new IllegalArgumentException("Profile id cannot be negative");
        }
    }

    public ProfileId() {
        this(0L);
    }
}
