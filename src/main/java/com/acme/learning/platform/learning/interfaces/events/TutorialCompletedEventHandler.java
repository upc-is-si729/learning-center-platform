package com.acme.learning.platform.learning.interfaces.events;

import com.acme.learning.platform.learning.domain.model.commands.UpdateStudentMetricsOnCourseCompletedCommand;
import com.acme.learning.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.acme.learning.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.platform.learning.domain.services.StudentCommandService;
import com.acme.learning.platform.shared.domain.events.TutorialCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TutorialCompletedEventHandler {
    private final StudentCommandService studentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    public TutorialCompletedEventHandler(StudentCommandService studentCommandService, EnrollmentQueryService enrollmentQueryService) {
        this.studentCommandService = studentCommandService;
        this.enrollmentQueryService = enrollmentQueryService;
    }

    @EventListener(TutorialCompletedEvent.class)
    public void on(TutorialCompletedEvent event) {
        var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(event.getEnrollmentId());
        var enrollment = enrollmentQueryService.handle(getEnrollmentByIdQuery);
        if (enrollment.isPresent()) {
            var updateStudentMetricsOnCourseCompletedCommand = new UpdateStudentMetricsOnCourseCompletedCommand(enrollment.get().getAcmeStudentRecordId());
            studentCommandService.handle(updateStudentMetricsOnCourseCompletedCommand);
        }
        System.out.println("TutorialCompletedEventHandler executed");
    }
}
