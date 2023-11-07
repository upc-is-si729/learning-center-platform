package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.aggregates.User;
import com.acme.learning.platform.iam.domain.model.queries.GetAllUsersQuery;

import java.util.List;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
}
