package com.acme.learning.platform.profiles.interfaces.rest.resources;

public record CreateProfileResource(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
}
