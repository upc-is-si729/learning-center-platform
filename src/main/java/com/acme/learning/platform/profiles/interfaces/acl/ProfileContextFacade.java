package com.acme.learning.platform.profiles.interfaces.acl;

import com.acme.learning.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learning.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.learning.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learning.platform.profiles.domain.services.ProfileCommandService;
import com.acme.learning.platform.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

/**
 * Service Facade for the Profile context.
 *
 * <p>
 * It is used by the other contexts to interact with the Profile context.
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by other contexts.
 * </p>
 *
 */
@Service
public class ProfileContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Creates a new Profile
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param streetAddress the street address
     * @param city the city
     * @param state the state
     * @param zipCode the zip code
     * @return the profile id
     */
    public Long createProfile(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, streetAddress, city, state, zipCode);
        return profileCommandService.handle(createProfileCommand);
    }

    /**
     * Fetches the profile id by email
     * @param email the email
     * @return the profile id
     */
    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();

    }
}
