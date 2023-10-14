package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class ProgressRecordItem {
    @Id
    private Long id;

    @Getter
    private Long enrollmentId;

    @Getter
    private Long tutorialId;

    private ProgressStatus status;

    private Date startedAt;

    private Date completedAt;

    public ProgressRecordItem(Long enrollmentId, Long tutorialId) {
        this.enrollmentId = enrollmentId;
        this.tutorialId = tutorialId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public ProgressRecordItem() {

    }

    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isCompleted() {
        return this.status == ProgressStatus.COMPLETED;
    }

    public boolean isInProgress() {
        return this.status == ProgressStatus.STARTED;
    }

    /**
     * Calculates the number of days elapsed since the item was started
     *
     * @return zero if not started. Otherwise, it returns the number of days elapsed between the started date and the completed date (if completed) or today
     */
    public long calculateDaysElapsed() {

        // If not started, return 0
        if (this.status == ProgressStatus.NOT_STARTED) return 0;

        var defaultTimeZone = java.time.ZoneId.systemDefault();
        // Only started items are registered, so it should be the started date
        var fromDate = this.startedAt.toInstant();
        // If completed it should be the completed date, otherwise it should be today
        var toDate = this.completedAt == null ? LocalDate.now().atStartOfDay(defaultTimeZone).toInstant() : this.completedAt.toInstant();

        return java.time.Duration.between(fromDate, toDate).toDays();
    }
}
