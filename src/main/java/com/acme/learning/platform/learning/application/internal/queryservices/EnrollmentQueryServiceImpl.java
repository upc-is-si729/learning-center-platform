package com.acme.learning.platform.learning.application.internal.queryservices;

import com.acme.learning.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.acme.learning.platform.learning.domain.model.queries.GetStudentEnrollmentsQuery;
import com.acme.learning.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.platform.learning.infrastructure.persistence.jpa.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of EnrollmentQueryService
 *
 * <p>
 *     This class is the implementation of the EnrollmentQueryService interface.
 *     It is used by the EnrollmentContext to handle queries on the Enrollment aggregate.
 * </p>
 *
 */
@Service
public class EnrollmentQueryServiceImpl implements EnrollmentQueryService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentQueryServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * Query handler to get student enrollments
     *
     * @param query containing studentRecordId
     * @return List of Enrollments
     */
    @Override
    public List<Enrollment> handle(GetStudentEnrollmentsQuery query) {
        return enrollmentRepository.findAllByAcmeStudentRecordId(query.studentRecordId());
    }

    /**
     * Query handler to get enrollment by id
     *
     * @param query containing enrollmentId
     * @return Enrollment
     */
    @Override
    public Optional<Enrollment> handle(GetEnrollmentByIdQuery query) {
        return enrollmentRepository.findById(query.enrollmentId());
    }
}
