package com.acme.learning.platform.learning.interfaces.rest.transform;

import com.acme.learning.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.learning.platform.learning.interfaces.rest.resources.LearningPathItemResource;

public class LearningPathItemResourceFromEntityAssembler {
    public static LearningPathItemResource toResourceFromEntity(LearningPathItem learningPathItem) {
        return new LearningPathItemResource(learningPathItem.getId(), learningPathItem.getCourse().getId(), learningPathItem.getTutorialId());
    }
}
