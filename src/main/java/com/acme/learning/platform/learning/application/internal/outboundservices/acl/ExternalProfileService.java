package com.acme.learning.platform.learning.application.internal.outboundservices.acl;

import com.acme.learning.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.learning.platform.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ExternalProfileService
 *
 * <p>
 *     This class is an outbound service used by the Learning Context to interact with the Profiles Context.
 *     It is implemented as part of an anti-corruption layer (ACL) to decouple the Learning Context from the Profiles Context.
 * </p>
 *
 */
@Service
public class ExternalProfileService {

    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    /**
     * Fetch profileId by email
     *
     * @param email the email to search for
     * @return profileId if found, empty otherwise
     */
    public Optional<ProfileId> fetchProfileIdByEmail(String email) {
        var profileId = profileContextFacade.fetchProfileIdByEmail(email);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

    /**
     * Create profile
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param streetAddress the street address
     * @param city the city
     * @param state the state
     * @param zipCode the zip code
     * @return profileId if created, empty otherwise
     */
    public Optional<ProfileId> createProfile(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
        var profileId = profileContextFacade.createProfile(firstName, lastName, email, streetAddress, city, state, zipCode);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

}
