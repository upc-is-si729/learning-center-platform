package com.acme.learning.platform.learning.interfaces.events;

import com.acme.learning.platform.learning.domain.model.commands.UpdateStudentMetricsOnCourseCompletedCommand;
import com.acme.learning.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.acme.learning.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.platform.learning.domain.services.StudentCommandService;
import com.acme.learning.platform.shared.domain.events.TutorialCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * TutorialCompletedEventHandler
 * <p>
 *     This event handler is responsible for handling TutorialCompletedEvent.
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
            // Update student metrics on course completed
            var updateStudentMetricsOnCourseCompletedCommand = new UpdateStudentMetricsOnCourseCompletedCommand(enrollment.get().getAcmeStudentRecordId());
            studentCommandService.handle(updateStudentMetricsOnCourseCompletedCommand);
        }
        System.out.println("TutorialCompletedEventHandler executed");
    }
}
