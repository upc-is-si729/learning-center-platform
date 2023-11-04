package com.acme.learning.platform.learning.domain.model.queries;

import com.acme.learning.platform.learning.domain.model.valueobjects.TutorialId;

public record GetCourseLearningPathItemByCourseIdAndTutorialIdQuery(Long courseId, Long tutorialId) {
}
