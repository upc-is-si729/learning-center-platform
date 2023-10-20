package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.learning.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * Represents a course.
 */
@Entity
public class Course  extends AuditableModel {
    @Getter
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
