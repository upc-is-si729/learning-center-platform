package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.learning.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

/**
 * Represents a course.
 */
@Getter
@Entity
public class Course  extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    /**
     * The learning path for this course.
     */
    @Embedded
    private final LearningPath learningPath;

    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.learningPath = new LearningPath();
    }

    public Course(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    /**
     * Updates the course information.
     * @param title The new title.
     * @param description The new description.
     * @return The updated course.
     */
    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    /**
     * Adds a tutorial to the learning path.
     * @param tutorialId The tutorial to add.
     */
    public void addTutorialToLearningPath(Long tutorialId) {
        System.out.println("Adding tutorial to learning path");
        this.learningPath.addItem(this, tutorialId);
    }

    /**
     * Adds a tutorial to the learning path.
     * @param tutorialId The tutorial to add.
     * @param nextTutorialId The id of the tutorial before which the new item should be added
     */
    public void addTutorialToLearningPath(Long tutorialId, Long nextTutorialId) {
        this.learningPath.addItem(this, tutorialId, nextTutorialId);
    }


}
