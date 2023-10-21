package com.acme.learning.platform.learning.domain.model.commands;

public record CreateStudentCommand(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
}
