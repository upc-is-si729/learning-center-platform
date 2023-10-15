package com.acme.learning.platform.learning.domain.model.entities;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * Represents an item in the learning path.
 */
@Entity
public class LearningPathItem {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Getter
    @ManyToOne(optional = false)
    private Tutorial tutorial;

    @Getter
    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LearningPathItem nextItem;

    public LearningPathItem(Course course, Tutorial tutorial, LearningPathItem nextItem) {
        this.course = course;
        this.tutorial = tutorial;
        this.nextItem = nextItem;
    }

    public LearningPathItem() {
        this.tutorial = null;
        this.nextItem = null;
    }

    public void updateNextItem(LearningPathItem nextItem) {
        this.nextItem = nextItem;
    }

}
