package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
public class Tutorial {
    @Getter
    @Id
    private Long id;

    private String title;

    private String description;

    private String contentUrl;

}
