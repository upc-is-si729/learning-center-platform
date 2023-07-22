package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Course {
    @Id
    private Long id;

    private String title;

    private String description;

    @Embedded
    private LearningPath learningPath;

    public LearningPath getLearningPath() {
        return learningPath;
    }


}
