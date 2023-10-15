package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.LearningPath;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * Represents a course.
 */
@Entity
public class Course {
    @Id
    private Long id;

    private String title;

    private String description;

    /**
     * The learning path for this course.
     */
    @Getter
    @Embedded
    private LearningPath learningPath;


}