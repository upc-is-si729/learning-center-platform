package com.acme.learning.platform.learning.domain.model.entities;

import com.acme.learning.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * Represents a tutorial.
 */
@Entity
public class Tutorial extends AuditableModel {
    @Getter
    @Id
    private Long id;

    private String title;

    private String description;

    private String contentUrl;

}
