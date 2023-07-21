package com.acme.learning.platform.learning.domain.model.entities;

public class LearningPathItem {
    private Long id;
    private Tutorial tutorial;

    private Long nextItemId;

    public LearningPathItem(Tutorial tutorial, Long nextItemId) {
        this.tutorial = tutorial;
        this.nextItemId = nextItemId;
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
