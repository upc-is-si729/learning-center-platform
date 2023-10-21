package com.acme.learning.platform.shared.domain.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * TutorialCompletedEvent
 * <p>
 *     This event is fired when a tutorial is completed.
 * </p>
 */
@Getter
public final class TutorialCompletedEvent extends ApplicationEvent {

    private final Long enrollmentId;

    private final Long tutorialId;

    public TutorialCompletedEvent(Object source, Long enrollmentId, Long tutorialId) {
        super(source);
        this.enrollmentId = enrollmentId;
        this.tutorialId = tutorialId;
    }

}
