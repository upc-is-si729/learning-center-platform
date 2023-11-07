package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.aggregates.User;
import com.acme.learning.platform.iam.domain.model.commands.SignInCommand;
import com.acme.learning.platform.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);


}
