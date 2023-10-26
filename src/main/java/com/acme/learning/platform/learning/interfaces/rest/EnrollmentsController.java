package com.acme.learning.platform.learning.interfaces.rest;

import com.acme.learning.platform.learning.domain.model.commands.CancelEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.ConfirmEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.RejectEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.acme.learning.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.learning.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.platform.learning.interfaces.rest.resources.RequestEnrollmentResource;
import com.acme.learning.platform.learning.interfaces.rest.resources.EnrollmentResource;
import com.acme.learning.platform.learning.interfaces.rest.transform.RequestEnrollmentCommandFromResourceAssembler;
import com.acme.learning.platform.learning.interfaces.rest.transform.EnrollmentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Inbound service for the Enrollment aggregate.
 * <p>
 * This controller is responsible for handling requests related to the Enrollment aggregate.
 * <p>
 *
 */
@RestController
@RequestMapping(value = "/api/v1/enrollments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Enrollments", description = "Enrollment Management Endpoints")
public class EnrollmentsController {
    private final EnrollmentCommandService enrollmentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    public EnrollmentsController(EnrollmentCommandService enrollmentCommandService, EnrollmentQueryService enrollmentQueryService) {
        this.enrollmentCommandService = enrollmentCommandService;
        this.enrollmentQueryService = enrollmentQueryService;
    }

    /**
     * Handles a request to enroll a student in a course.
     *
     * @param resource The request body containing the student record ID and the course ID.
     * @return The enrollment resource.
     */
    @PostMapping
    public ResponseEntity<EnrollmentResource> requestEnrollment(@RequestBody RequestEnrollmentResource resource) {
        var command = RequestEnrollmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var enrollmentId = enrollmentCommandService.handle(command);
        if (enrollmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(enrollmentId);
        var enrollment = enrollmentQueryService.handle(getEnrollmentByIdQuery);
        if (enrollment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var enrollmentResource = EnrollmentResourceFromEntityAssembler.toResourceFromEntity(enrollment.get());
        return new ResponseEntity<>(enrollmentResource, HttpStatus.CREATED);
    }

    /**
     * Handles a request to confirm an enrollment.
     *
     * @param enrollmentId The enrollment ID.
     * @return The enrollment ID.
     */
    @PostMapping("/{enrollmentId}/confirmations")
    public ResponseEntity<?> confirmEnrollment(@PathVariable Long enrollmentId) {
        var command = new ConfirmEnrollmentCommand(enrollmentId);
        var confirmedEnrollmentId = enrollmentCommandService.handle(command);
        return ResponseEntity.ok(confirmedEnrollmentId);
    }

    /**
     * Handles a request to reject an enrollment.
     *
     * @param enrollmentId The enrollment ID.
     * @return The enrollment ID.
     */
    @PostMapping("/{enrollmentId}/rejections")
    public ResponseEntity<?> rejectEnrollment(@PathVariable Long enrollmentId) {
        var command = new RejectEnrollmentCommand(enrollmentId);
        var rejectedEnrollmentId = enrollmentCommandService.handle(command);
        return ResponseEntity.ok(rejectedEnrollmentId);
    }

    /**
     * Handles a request to cancel an enrollment.
     *
     * @param enrollmentId The enrollment ID.
     * @return The enrollment ID.
     */
    @PostMapping("/{enrollmentId}/cancellations")
    public ResponseEntity<?> cancelEnrollment(@PathVariable Long enrollmentId) {
        var command = new CancelEnrollmentCommand(enrollmentId);
        var cancelledEnrollmentId = enrollmentCommandService.handle(command);
        return ResponseEntity.ok(cancelledEnrollmentId);
    }
}
