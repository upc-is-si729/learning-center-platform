package com.acme.learning.platform.iam.application.internal.commandservices;

import com.acme.learning.platform.iam.domain.model.commands.SeedRolesCommand;
import com.acme.learning.platform.iam.domain.services.RoleCommandService;
import org.springframework.stereotype.Service;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    @Override
    public void handle(SeedRolesCommand command) {

    }
}
