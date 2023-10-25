package com.acme.learning.platform.learning.interfaces.rest.transform;

import com.acme.learning.platform.learning.domain.model.aggregates.Student;
import com.acme.learning.platform.learning.interfaces.rest.resources.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student student) {
        return new StudentResource(
            student.getStudentRecordId(),
            student.getProfileId()
        );
    }
}
