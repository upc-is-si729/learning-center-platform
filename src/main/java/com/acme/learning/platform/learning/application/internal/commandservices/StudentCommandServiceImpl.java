package com.acme.learning.platform.learning.application.internal.commandservices;

import com.acme.learning.platform.learning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.learning.platform.learning.domain.model.aggregates.Student;
import com.acme.learning.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.learning.platform.learning.domain.model.commands.UpdateStudentMetricsOnCourseCompletedCommand;
import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.platform.learning.domain.services.StudentCommandService;
import com.acme.learning.platform.learning.infrastructure.repositories.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of StudentCommandService
 *
 * <p>
 *     This class is the implementation of the StudentCommandService interface.
 *     It is used by the LearningContext to handle commands on the Student aggregate.
 * </p>
 *
 */
@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;
    private final ExternalProfileService externalProfileService;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ExternalProfileService externalProfileService) {
        this.studentRepository = studentRepository;
        this.externalProfileService = externalProfileService;
    }

    //**
    // * Command handler to create student
    // *
    // * @param command containing student details
    // * @return AcmeStudentRecordId
    // */
    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {

        // Fetch profileId by email
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());

        // If profileId is empty, create profile
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.firstName(), command.lastName(), command.email(), command.streetAddress(), command.city(), command.state(), command.zipCode());
        } else {
            // If profileId is not empty, check if student exists
            studentRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                throw new IllegalArgumentException("Student already exists");
            });
        }

        // If profileId is still empty, throw exception
        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

        // Create student using fetched or created profileId
        var student = new Student(profileId.get());
        studentRepository.save(student);
        return student.getAcmeStudentRecordId();
    }

    //**
    // * Command handler to update student metrics on course completed
    // *
    // * @param command containing studentRecordId
    // * @return AcmeStudentRecordId
    // */
    @Override
    public AcmeStudentRecordId handle(UpdateStudentMetricsOnCourseCompletedCommand command) {
        studentRepository.findByAcmeStudentRecordId(command.studentRecordId()).map(student -> {
            // Update student metrics
            student.updateMetricsOnCourseCompleted();
            studentRepository.save(student);
            return student.getAcmeStudentRecordId();
        }).orElseThrow(() -> new IllegalArgumentException("Student with given Id not found"));
        return null;
    }
}
