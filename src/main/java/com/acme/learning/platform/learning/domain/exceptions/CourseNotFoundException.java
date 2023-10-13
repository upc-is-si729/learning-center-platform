package com.acme.learning.platform.learning.domain.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long aLong) {
        super("Course with id " + aLong + " not found");
    }
}
