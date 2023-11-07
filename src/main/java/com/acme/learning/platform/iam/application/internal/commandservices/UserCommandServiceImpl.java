package com.acme.learning.platform.iam.application.internal.commandservices;

import com.acme.learning.platform.iam.domain.model.aggregates.User;
import com.acme.learning.platform.iam.domain.model.commands.SignInCommand;
import com.acme.learning.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.learning.platform.iam.domain.services.UserCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    @Override
    public Optional<User> handle(SignInCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        return Optional.empty();
    }
}
