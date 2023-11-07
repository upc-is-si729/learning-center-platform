package com.acme.learning.platform.learning.domain.model.commands;

public record AddTutorialToCourseLearningPathCommand(Long tutorialId, Long courseId) {
}
