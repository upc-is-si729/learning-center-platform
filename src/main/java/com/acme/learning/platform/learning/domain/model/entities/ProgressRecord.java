package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {

    @OneToMany
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }

    public void initializeProgressRecord(Long enrollmentId, LearningPath learningPath) {
        Long  tutorialId = learningPath.getFirstTutorialIdInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollmentId, tutorialId);
        progressRecordItems.add(progressRecordItem);
    }


    public void startTutorial(Long tutorialId) {

        if (hasAnItemInProgress()) throw new IllegalStateException("A tutorial is already in progress");

        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.start();
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");
    }
    public void completeTutorial(Long tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.complete();
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");

        if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;

        Tutorial nextTutorial = learningPath.getNextTutorialInLearningPath(tutorialId);
        if (nextTutorial != null) {
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollmentId(), nextTutorial.getId());
            progressRecordItems.add(nextProgressRecordItem);
        }
    }

    private ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId) {
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    private boolean hasAnItemInProgress() {
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    public long calculateDaysElapsedForEnrollment(Long enrollmentId) {
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getEnrollmentId().equals(enrollmentId))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed).sum();
    }

}
