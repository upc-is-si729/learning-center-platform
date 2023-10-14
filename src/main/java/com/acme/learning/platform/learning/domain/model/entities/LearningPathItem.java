package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class LearningPathItem {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    private Course course;

    @Getter
    @ManyToOne(optional = false)
    private Tutorial tutorial;

    @Getter
    private Long nextItemId;

    public LearningPathItem(Course course, Tutorial tutorial, Long nextItemId) {
        this.course = course;
        this.tutorial = tutorial;
        this.nextItemId = nextItemId;
    }

    public LearningPathItem() {
        this.tutorial = null;
        this.nextItemId = null;
    }

    public void updateNextItemId(Long nextItemId) {
        this.nextItemId = nextItemId;
    }

}
