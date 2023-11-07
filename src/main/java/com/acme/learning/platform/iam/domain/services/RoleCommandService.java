package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
