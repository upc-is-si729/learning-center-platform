package com.acme.learning.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the tutorial id.
 */
@Embeddable
public record TutorialId(Long tutorialId) {

        public TutorialId {
            if (tutorialId < 0) {
                throw new IllegalArgumentException("Tutorial tutorialId cannot be negative");
            }
        }

        public TutorialId() {
            this(0L);
        }
}
