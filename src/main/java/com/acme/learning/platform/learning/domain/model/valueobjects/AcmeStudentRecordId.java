package com.acme.learning.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record AcmeStudentRecordId(String id) {
    public AcmeStudentRecordId() {
        this(UUID.randomUUID().toString());
    }

    public AcmeStudentRecordId {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Acme student record id cannot be null or blank");
        }
    }
}
