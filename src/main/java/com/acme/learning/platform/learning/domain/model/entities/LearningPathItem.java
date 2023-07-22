package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class LearningPathItem {
    @Id
    private Long id;

    @ManyToOne(optional = false)
    private Tutorial tutorial;

    private Long nextItemId;

    public LearningPathItem(Tutorial tutorial, Long nextItemId) {
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

    public Long getNextItemId() {
        return nextItemId;
    }

    public Long getId() {
        return id;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

}
