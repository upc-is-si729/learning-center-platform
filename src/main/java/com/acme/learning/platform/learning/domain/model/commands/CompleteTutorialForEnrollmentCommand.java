package com.acme.learning.platform.learning.domain.model.commands;

public record CompleteTutorialForEnrollmentCommand(Long enrollmentId, Long tutorialId) {
}
