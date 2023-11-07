package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.entities.Role;
import com.acme.learning.platform.iam.domain.model.queries.GetAllRolesQuery;

import java.util.List;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
}
