package com.acme.learning.platform.learning.domain.model.commands;

import com.acme.learning.platform.learning.domain.model.valueobjects.TutorialId;

public record AddTutorialToCourseLearningPathCommand(Long tutorialId, Long courseId) {
}
