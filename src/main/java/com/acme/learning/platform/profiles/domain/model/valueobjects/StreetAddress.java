package com.acme.learning.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

@Embeddable
public record StreetAddress(
        String street,
        String number,
        String city,
        String zipCode,
        String country
) {
    public StreetAddress() {
        this(null, null, null, null, null);
    }
}

