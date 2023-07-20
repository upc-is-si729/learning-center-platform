package com.acme.learning.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record AcmeStudentRecordId(String id) {
    public AcmeStudentRecordId() {
        this(UUID.randomUUID().toString());
    }
}
