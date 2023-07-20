package com.acme.learning.platform.learning.domain.model.entities;

import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.learning.platform.learning.domain.model.valueobjects.StudentPerformanceMetrics;
import com.acme.learning.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;

@Entity
public class Student extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(name = "acme_student_id")
    private AcmeStudentRecordId acmeStudentId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private StudentPerformanceMetrics performanceMetrics;

    public Student() {
        this.acmeStudentId = new AcmeStudentRecordId();
        this.performanceMetrics = new StudentPerformanceMetrics();
    }

    public Student(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public void updateMetricsOnCourseCompleted() {
        this.performanceMetrics = this.performanceMetrics.incrementTotalCompletedCourses();
    }

    public void updateMetricsOnTutorialCompleted() {
        this.performanceMetrics = this.performanceMetrics.incrementTotalTutorials();
    }

    public String getStudentRecordId() {
        return this.acmeStudentId.id();
    }

    public Long getProfileId() {
        return this.profileId.id();
    }




}
