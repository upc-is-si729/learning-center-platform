package com.acme.learning.platform.learning.interfaces.rest.transform;

import com.acme.learning.platform.learning.domain.model.queries.GetStudentEnrollmentsQuery;
import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.platform.learning.interfaces.rest.resources.EnrollmentResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students/{studentRecordId}/enrollments")
public class StudentEnrollmentsController {
    private final EnrollmentQueryService enrollmentQueryService;


    public StudentEnrollmentsController(EnrollmentQueryService enrollmentQueryService) {
        this.enrollmentQueryService = enrollmentQueryService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResource>> getEnrollmentsForStudentWithStudentRecordId(@PathVariable String studentRecordId) {
        var acmeStudentRecordId = new AcmeStudentRecordId(studentRecordId);
        var getStudentEnrollmentsQuery = new GetStudentEnrollmentsQuery(acmeStudentRecordId);
        var enrollments = enrollmentQueryService.handle(getStudentEnrollmentsQuery);
        var enrollmentResources = enrollments.stream().map(EnrollmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(enrollmentResources);
    }

}
