package com.acme.learning.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PersonName(String firstName, String lastName) {
    public PersonName() {
        this(null, null);
    }
}
