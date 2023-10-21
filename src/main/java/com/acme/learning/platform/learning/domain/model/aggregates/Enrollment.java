package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.learning.platform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.platform.learning.domain.model.events.TutorialCompletedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Represents an enrollment.
 * The enrollment is an aggregate root.
 */
@EntityListeners(AuditingEntityListener.class)
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

    /**
     * The progress record for this enrollment.
     */
    @Embedded
    private ProgressRecord progressRecord;

    private EnrollmentStatus status;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

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

    public String getStatus() {
        return this.status.name().toLowerCase();
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

    public void completeTutorial(Long tutorialId) {
        progressRecord.completeTutorial(tutorialId, course.getLearningPath());
        // Publish a Tutorial Completed Event
        this.registerEvent(new TutorialCompletedEvent(this, this.getId(), tutorialId));
    }
}
