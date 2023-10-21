package com.acme.learning.platform.profiles.interfaces.rest.transform;

import com.acme.learning.platform.profiles.domain.model.aggregates.Profile;
import com.acme.learning.platform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile profile) {
        return new ProfileResource(profile.getId(), profile.getFullName(), profile.getEmailAddress(), profile.getStreetAddress());
    }
}
