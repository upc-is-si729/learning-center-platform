package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.entities.Course;
import com.acme.learning.platform.learning.domain.model.entities.ProgressRecord;
import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
public class Enrollment extends AbstractAggregateRoot<Enrollment> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Embedded
    private ProgressRecord progressRecord;

    private EnrollmentStatus status;

    public Enrollment() {
    }

    public Enrollment(AcmeStudentRecordId studentRecordId, Course course) {
        this.acmeStudentRecordId = studentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
        this.progressRecord.initializeProgressRecord(this, course.getLearningPath());
        // this.registerEvent(new EnrollmentConfirmedEvent(this));
    }

    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
        // this.registerEvent(new EnrollmentRejectedEvent(this));
    }

    public void cancel() {
        this.status = EnrollmentStatus.CANCELLED;
        // this.registerEvent(new EnrollmentCancelledEvent(this));
    }

    public long calculateDaysElapsed() {
        return progressRecord.calculateDaysElapsedForEnrollment(this);
    }

    public boolean isConfirmed() {
        return this.status == EnrollmentStatus.CONFIRMED;
    }

    public boolean isRejected() {
        return this.status == EnrollmentStatus.REJECTED;
    }
}
