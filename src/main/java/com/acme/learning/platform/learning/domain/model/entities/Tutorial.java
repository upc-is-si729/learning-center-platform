package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tutorial {
    @Id
    private Long id;

    private String title;

    private String description;

    private String contentUrl;

    public Long getId() {
        return id;
    }
}
