package com.acme.learning.platform.profiles.interfaces.rest.transform;

import com.acme.learning.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learning.platform.profiles.interfaces.rest.resources.CreateProfileResource;

/**
 * CreateProfileCommandFromResourceAssembler
 *
 * <p>
 *     Assembler to create a {@link CreateProfileCommand} from a {@link CreateProfileResource}
 *     <br>
 *     This class applies the assembler pattern to transform a CreateProfileResource resource into a CreateProfileCommand command
 *     </br>
 * </p>
 */
public class CreateProfileCommandFromResourceAssembler {
    /**
     * toCommandFromResource
     * @param resource {@link CreateProfileResource} the resource to transform
     * @return {@link CreateProfileCommand} command the resulting command
     */
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(), resource.lastName(), resource.email(), resource.street(), resource.number(), resource.city(), resource.state(), resource.zipCode());
    }
}
