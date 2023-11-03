package com.acme.learning.platform.learning.domain.model.valueobjects;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.platform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a learning path of tutorials for a course
 * It is embedded in the Course aggregate
 * It is a value object that is not persisted in the database
 * It is initialized when a course is created
 * It is updated when a tutorial is added to the course
 * It is the base reference to track the progress of a student in a course
 * It is used to determine the next tutorial to be completed by a student
 */
@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course")
    private List<LearningPathItem> learningPathItems;

    public LearningPath() {
        this.learningPathItems = new ArrayList<>();
    }


    /**
     * Adds the item before the item with the given id
     * @param course The course to add
     * @param tutorialId The tutorial to add
     * @param nextItem The id of the item before which the new item should be added
     */
    public void addItem(Course course, TutorialId tutorialId, LearningPathItem nextItem) {
        // Add the new item before the next item
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, nextItem);
        learningPathItems.add(learningPathItem);
    }

    /**
     * Adds the item at the end of the learning path
     * @param course The course to add
     * @param tutorialId The tutorial to add
     */
    public void addItem(Course course, TutorialId tutorialId) {
        LearningPathItem originalLastItem = getLastItemInLearningPath();
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, null);
        learningPathItems.add(learningPathItem);
        if (originalLastItem != null) originalLastItem.updateNextItem(learningPathItem);
    }

    /**
     * Adds the item at the end of the learning path
     * @param course The course to add
     * @param tutorialId The tutorial to add
     * @param nextTutorialId The id of the tutorial before which the new item should be added
     */
    public void addItem(Course course, TutorialId tutorialId, TutorialId nextTutorialId) {
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(nextTutorialId);
        addItem(course, tutorialId, nextItem);
    }

    public TutorialId getFirstTutorialIdInLearningPath() {
        return learningPathItems.get(0).getTutorialId();
    }

    public TutorialId getFirstTutorialInLearningPath() {
        return learningPathItems.get(0).getTutorialId();
    }

    public TutorialId getNextTutorialInLearningPath(TutorialId currentTutorialId) {
        LearningPathItem item = getLearningPathItemWithTutorialId(currentTutorialId);
        return item != null ? item.getTutorialId() : null;
    }

    private LearningPathItem getLearningPathItemWithId(Long itemId) {
        return learningPathItems.stream().filter(learningPathItem -> learningPathItem.getId().equals(itemId))
                .findFirst().orElse(null);
    }

    private LearningPathItem getLearningPathItemWithTutorialId(TutorialId tutorialId) {
        return learningPathItems.stream().filter(learningPathItem -> learningPathItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    public boolean isLastTutorialInLearningPath(TutorialId currentTutorialId) {
        return getNextTutorialInLearningPath(currentTutorialId) == null;
    }

    public LearningPathItem getLastItemInLearningPath() {
        return learningPathItems.stream().filter(item -> item.getNextItem() == null)
                .findFirst().orElse(null);
    }

    public boolean isEmpty() {
        return learningPathItems.isEmpty();
    }
}
