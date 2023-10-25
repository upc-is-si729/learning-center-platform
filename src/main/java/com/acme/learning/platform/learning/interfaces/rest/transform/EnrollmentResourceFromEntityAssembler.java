package com.acme.learning.platform.learning.interfaces.rest.transform;

import com.acme.learning.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.platform.learning.interfaces.rest.resources.EnrollmentResource;

/**
 * EnrollmentResourceFromEntityAssembler.
 * <p>
 * This class is used to transform an Enrollment entity into an EnrollmentResource.
 * </p>
 */
public class EnrollmentResourceFromEntityAssembler {
    /**
     * Transform an Enrollment entity into an EnrollmentResource.
     *
     * @param enrollment Enrollment entity to be transformed.
     * @return EnrollmentResource the resulting resource.
     */
    public static EnrollmentResource toResourceFromEntity(Enrollment enrollment) {
        return new EnrollmentResource(enrollment.getId(), enrollment.getAcmeStudentRecordId().studentRecordId(), enrollment.getCourse().getId(), enrollment.getStatus());
    }
}
