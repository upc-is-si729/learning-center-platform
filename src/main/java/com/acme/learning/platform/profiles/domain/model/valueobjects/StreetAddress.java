package com.acme.learning.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Street Address
 * <p>
 * Value object representing a street address.
 * </p>
 */
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

    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
    }

    public StreetAddress(String street, String city, String zipCode, String country) {
        this(street, null, city, zipCode, country);
    }

    public String getStreetAddress() {
        return String.format("%s %s %s %s %s", street, number, city, zipCode, country);
    }
}

