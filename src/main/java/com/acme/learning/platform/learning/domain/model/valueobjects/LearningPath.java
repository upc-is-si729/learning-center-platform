package com.acme.learning.platform.learning.domain.model.valueobjects;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.learning.platform.learning.domain.model.entities.Tutorial;
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
     * @param tutorial The tutorial to add
     * @param nextItem The id of the item before which the new item should be added
     */
    public void addItem(Course course, Tutorial tutorial, LearningPathItem nextItem) {
        // Add the new item before the next item
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorial, nextItem);
        learningPathItems.add(learningPathItem);
    }

    /**
     * Adds the item at the end of the learning path
     * @param course The course to add
     * @param tutorial The tutorial to add
     */
    public void addItem(Course course, Tutorial tutorial) {
        LearningPathItem originalLastItem = getLastItemInLearningPath();
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorial, null);
        learningPathItems.add(learningPathItem);
        if (originalLastItem != null) originalLastItem.updateNextItem(learningPathItem);
    }

    public Long getFirstTutorialIdInLearningPath() {
        return learningPathItems.get(0).getTutorial().getId();
    }

    public Tutorial getFirstTutorialInLearningPath() {
        return learningPathItems.get(0).getTutorial();
    }

    public Tutorial getNextTutorialInLearningPath(Long currentTutorialId) {
        LearningPathItem item = getLearningPathItemWithTutorialId(currentTutorialId);
        return item != null ? item.getTutorial() : null;
    }

    private LearningPathItem getLearningPathItemWithId(Long itemId) {
        return learningPathItems.stream().filter(learningPathItem -> learningPathItem.getId().equals(itemId))
                .findFirst().orElse(null);
    }

    private LearningPathItem getLearningPathItemWithTutorialId(Long tutorialId) {
        return learningPathItems.stream().filter(learningPathItem -> learningPathItem.getTutorial().getId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    public boolean isLastTutorialInLearningPath(Long currentTutorialId) {
        return getNextTutorialInLearningPath(currentTutorialId) == null;
    }

    public LearningPathItem getLastItemInLearningPath() {
        return learningPathItems.stream().filter(item -> item.getNextItem() == null)
                .findFirst().orElse(null);
    }
}
