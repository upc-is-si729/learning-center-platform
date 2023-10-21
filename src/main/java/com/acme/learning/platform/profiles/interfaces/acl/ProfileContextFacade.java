package com.acme.learning.platform.profiles.interfaces.acl;

import com.acme.learning.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learning.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.learning.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learning.platform.profiles.domain.services.ProfileCommandService;
import com.acme.learning.platform.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProfileContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    public Long createProfile(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, streetAddress, city, state, zipCode);
        return profileCommandService.handle(createProfileCommand);
    }

    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();

    }
}
