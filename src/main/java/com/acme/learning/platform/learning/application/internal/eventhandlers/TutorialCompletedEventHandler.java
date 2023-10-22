package com.acme.learning.platform.learning.application.internal.eventhandlers;

import com.acme.learning.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.learning.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.acme.learning.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.platform.learning.domain.services.StudentCommandService;
import com.acme.learning.platform.learning.domain.model.events.TutorialCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * TutorialCompletedEventHandler
 * <p>
 *     This event handler is responsible for handling TutorialCompletedEvent.
 *     It uses EventListener from Spring Boot Context Event Bus to listen to TutorialCompletedEvent.
 * </p>
 */
@Service
public class TutorialCompletedEventHandler {
    private final StudentCommandService studentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    public TutorialCompletedEventHandler(StudentCommandService studentCommandService, EnrollmentQueryService enrollmentQueryService) {
        this.studentCommandService = studentCommandService;
        this.enrollmentQueryService = enrollmentQueryService;
    }

    /**
     * Event handler for TutorialCompletedEvent
     *
     * @param event TutorialCompletedEvent containing enrollmentId and tutorialId
     *
     */
    @EventListener(TutorialCompletedEvent.class)
    public void on(TutorialCompletedEvent event) {
        // Fetch enrollment by enrollmentId
        var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(event.getEnrollmentId());
        var enrollment = enrollmentQueryService.handle(getEnrollmentByIdQuery);
        if (enrollment.isPresent()) {
            // Update student metrics on tutorial completed
            var updateStudentMetricsOnTutorialCompletedCommand = new UpdateStudentMetricsOnTutorialCompletedCommand(enrollment.get().getAcmeStudentRecordId());
            studentCommandService.handle(updateStudentMetricsOnTutorialCompletedCommand);
        }
        System.out.println("TutorialCompletedEventHandler executed");
    }
}