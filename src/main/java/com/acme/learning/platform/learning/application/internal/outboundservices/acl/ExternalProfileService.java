package com.acme.learning.platform.learning.application.internal.outboundservices.acl;

import com.acme.learning.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.learning.platform.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileService {

    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<ProfileId> fetchProfileIdByEmail(String email) {
        var profileId = profileContextFacade.fetchProfileIdByEmail(email);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

    public Optional<ProfileId> createProfile(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
        var profileId = profileContextFacade.createProfile(firstName, lastName, email, streetAddress, city, state, zipCode);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

}
